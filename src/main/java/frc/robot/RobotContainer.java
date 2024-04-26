// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Subsystems.Climber;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Transmission;

public class RobotContainer {
  // Subsistemas del robot (Transmision, shooter, intake, climber)
  private Transmission drive = new Transmission();
  private Shooter shooter = new Shooter();
  private Intake intake = new Intake();
  private Climber climber = new Climber();
  // Controles para el robot (control y dpad)
  private CommandXboxController xbox = new CommandXboxController(0);
  private POVButton down = new POVButton(xbox.getHID(), 0);
  private POVButton left = new POVButton(xbox.getHID(), 270);
  private POVButton up = new POVButton(xbox.getHID(), 180);
  private POVButton right = new POVButton(xbox.getHID(), 90);

  public RobotContainer() {
    configureBindings();
    // Ejecucion del comando para controlar la transmision
    drive.setDefaultCommand(drive.move(() -> xbox.getLeftY(), () -> xbox.getRightX()));    
    // Activar comandos del robot (lanzar, recargar)
    xbox.a().onTrue(shooter.shoot()).onFalse(shooter.stop());
    xbox.x().onTrue(shooter.recharge()).onFalse(shooter.stop());
    xbox.b().onTrue(intake.shoot()).onFalse(intake.stop());
    xbox.y().onTrue(intake.recharge()).onFalse(intake.stop());
  }

  private void configureBindings() {
    // Acciones no observables del climber, cambiar direccion
    down.onTrue(climber.change_direction(-1));
    up.onTrue(climber.change_direction(1));
    // Activar el climber (por lado o completo)
    left.onTrue(climber.activate_left()).onFalse(climber.stop());
    right.onTrue(climber.activate_right()).onFalse(climber.stop());
    xbox.leftBumper().onTrue(climber.activate()).onFalse(climber.stop());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
