package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

public class SimpleMotorSubsystem extends SubsystemBase {

    private final TalonFX motor;

    public SimpleMotorSubsystem() { //Controller Area Network = CAN
        motor = new TalonFX(1, "can"); // device ID 1, CAN bus "can"
    }

    /**
     * Set the speed of the roller motor
     * @param speed target speed in RPS
     */
    public void setMotorSpeed(double speed){
        motor.set(speed);
    }

    // Resets the motor's current position to represent 0
    public void resetMotorRotation(){
        motor.setPosition(0);
    }

    // Get the current velocity (rotations per second)
    public double getMotorVelocity() {
        return motor.getRotorVelocity().getValueAsDouble();
    }

    // Get the motor's current position (rotations)
    public double getMotorRotation() {
        return motor.getRotorPosition().getValueAsDouble();
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }
}