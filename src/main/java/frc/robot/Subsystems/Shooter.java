package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    CANSparkMax shooter_up = new CANSparkMax(7, MotorType.kBrushless);
    CANSparkMax shooter_down = new CANSparkMax(6, MotorType.kBrushless);
    MotorGroup shooter = new MotorGroup(shooter_up, shooter_down);
    RelativeEncoder up = shooter_up.getEncoder();

    public Shooter(){
        up.setPosition(0);
    } 

    public Command shoot(){
        return run(() -> shooter.set(0.7));
    }

    public Command recharge(){
        return run(() -> shooter.set(-0.4));
    }

    public Command stop(){
        return run(() -> shooter.set(0));
    }

    public void periodic(){
        SmartDashboard.putNumber("encoder", up.getPosition());
    }
}
