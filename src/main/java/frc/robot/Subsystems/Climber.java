package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    private int direction = 1;
    private double vel = 0.5;
    private VictorSP left;
    private VictorSP right;
    private MotorGroup total = new MotorGroup(right, left);

    public Climber(){
        left = new VictorSP(2);
        right = new VictorSP(1);
    }

    public Command activate(){
        return run(() -> total.set(direction * vel));
    }

    public Command activate_right(){
        return run(() -> right.set(direction * vel));
    }

    public Command activate_left(){
        return run(() -> left.set(direction * vel));
    }

    public Command stop(){
        return run(() -> total.set(0));
    }

    public void change(int dir){
        direction = dir;
    }

    public Command change_direction(int dir){
        return runOnce(() -> change(dir));
    }

    public void periodic(){
        SmartDashboard.putNumber("Climber dir", direction);
    }
}
