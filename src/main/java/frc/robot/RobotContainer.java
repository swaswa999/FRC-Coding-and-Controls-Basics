// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SimpleMotorSubsystem;
import frc.robot.commands.SimpleMotorCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  private final CommandPS5Controller driveController = new CommandPS5Controller(0);
  
  
  private final SimpleMotorSubsystem simpleMotor = new SimpleMotorSubsystem();

  private final Trigger driveLBumper;
  private final Trigger driveRBumper;
  private final Trigger driveRTrigger;
  private final Trigger driveLTrigger;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    driveLBumper = driveController.L1();
    driveRBumper = driveController.R1();
    driveRTrigger = new Trigger(() -> driveController.getR2Axis() > 0.1); //this means it wont activate till at 0.1 (drift)
    driveLTrigger = new Trigger(() -> driveController.getL2Axis() > 0.1);


    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    driveLBumper.onTrue(
      new SimpleMotorCommand(simpleMotor));

    driveRBumper.onTrue(
        new SimpleMotorCommand(simpleMotor));

    driveRTrigger.whileTrue(new RunCommand(
        () -> simpleMotor.setMotorSpeed(driveController.getR2Axis()), simpleMotor));

    driveLTrigger.whileTrue(new edu.wpi.first.wpilibj2.command.RunCommand(
        () -> simpleMotor.setMotorSpeed(-driveController.getL2Axis()), simpleMotor));
  }



}
