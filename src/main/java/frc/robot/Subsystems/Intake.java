package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    private VictorSP intake;
    public Intake(){
        intake = new VictorSP(9);
    }

    public Command shoot(){
        return run(() -> intake.set(0.8));
    }

    public Command recharge(){
        return run(() -> intake.set(-0.4));
    }

    public Command stop(){
        return run(() -> intake.set(0));
    }
}
