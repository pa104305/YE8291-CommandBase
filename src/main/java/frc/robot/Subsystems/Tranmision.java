package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tranmision extends SubsystemBase{
    CANSparkMax leftFront = new CANSparkMax(4, MotorType.kBrushless);
    CANSparkMax leftBack = new CANSparkMax(2, MotorType.kBrushless);
    CANSparkMax rightFront = new CANSparkMax(3, MotorType.kBrushless);
    CANSparkMax rightBack = new CANSparkMax(1, MotorType.kBrushless);
    DifferentialDrive drive = new DifferentialDrive(leftBack, rightBack);

    public Tranmision(){
        leftFront.follow(leftBack);
        rightFront.follow(rightBack);
        rightBack.setInverted(true);
    }

    public Command move(double lineal, double turn){
        return run(() -> drive.arcadeDrive(lineal * 0.5, turn * 0.5));
    }

    public Command move(DoubleSupplier lineal, DoubleSupplier turn){
        return run(() -> drive.arcadeDrive(lineal.getAsDouble()*0.5, turn.getAsDouble()*0.5));
    }

    public void periodic(){
        drive.feed();
    }
}
