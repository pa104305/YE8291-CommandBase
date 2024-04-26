package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorGroup extends SubsystemBase{
    MotorController motor_one;
    MotorController motor_two;

    public MotorGroup(MotorController motor_one, MotorController motor_two){
        this.motor_one = motor_one;
        this.motor_two = motor_two;
    }

    public void set(double vel){
        motor_one.set(vel);
        motor_two.set(vel);
    }
}
