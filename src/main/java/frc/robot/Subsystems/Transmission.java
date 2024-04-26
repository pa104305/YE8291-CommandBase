package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transmission extends SubsystemBase {
    private CANSparkMax eng_l_f = new CANSparkMax(4, MotorType.kBrushed);
    private CANSparkMax eng_l_b = new CANSparkMax(2, MotorType.kBrushed);
    private CANSparkMax eng_r_f = new CANSparkMax(3, MotorType.kBrushed);
    private CANSparkMax eng_r_b = new CANSparkMax(1, MotorType.kBrushed);
    private DifferentialDrive drive = new DifferentialDrive(eng_l_f, eng_r_f);
    private double vel = -0.75;

    public Transmission (){
        this.eng_l_b.restoreFactoryDefaults();
        this.eng_l_f.restoreFactoryDefaults();
        this.eng_r_b.restoreFactoryDefaults();
        this.eng_r_f.restoreFactoryDefaults();
        eng_l_b.follow(eng_l_f);
        eng_r_b.follow(eng_r_f);
        eng_l_f.setInverted(true);
    }

    public void set_vel(double in_vel){
        vel = -in_vel;
    }

    public Command slower(double in_vel){
        return run(() -> set_vel(in_vel));
    }

    public Command move(double lineal, double turn) {
        return run(() -> drive.arcadeDrive(lineal*vel, turn*vel));
    }

    public Command move(DoubleSupplier speed, DoubleSupplier rotation) {
        return run(() -> drive.arcadeDrive(speed.getAsDouble() * vel, rotation.getAsDouble() * vel));
    }

    public void periodic(){
        SmartDashboard.putNumber("vel", vel);
    }
}
