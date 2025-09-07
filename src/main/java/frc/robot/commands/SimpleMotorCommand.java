package frc.robot.commands;

import frc.robot.subsystems.SimpleMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class SimpleMotorCommand extends Command {
  private final SimpleMotorSubsystem motorSubsystem;
  private boolean goingForward = true;

  public SimpleMotorCommand(SimpleMotorSubsystem subsystem) {
    this.motorSubsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    motorSubsystem.resetMotorRotation(); // Start at 0
  }

  @Override
  public void execute() {
    double pos = motorSubsystem.getMotorRotation();

    if (goingForward) {
      motorSubsystem.setMotorSpeed(0.3);
      if (pos >= 5.0) {
        goingForward = false; // Switch to reverse
      }
    } else {
      motorSubsystem.setMotorSpeed(-0.3);
    }
  }

  @Override
  public void end(boolean interrupted) {
    motorSubsystem.setMotorSpeed(0); // Always stop
  }

  @Override
  public boolean isFinished() {
    return !goingForward && motorSubsystem.getMotorRotation() <= 0.0;
  }
}