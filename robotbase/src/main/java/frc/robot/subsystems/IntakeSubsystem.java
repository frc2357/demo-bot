package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Value;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/** The subsystem representing the intake rollers, which are controlled by a single motor. */
public class IntakeSubsystem extends SubsystemBase {
  private final TalonFX mainMotor = new TalonFX(IntakeConstants.kMainMotorPort);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    SendableRegistry.addChild(this, mainMotor);
  }

  /**
   * Sets the speed of the intake's motors.
   * 
   * @param speed The speed to set the motor to.
   */
  public void setSpeed(double speed) {
    mainMotor.set(speed);
  }

  /**
   * Sets the speed of the intake's motors using the Units library.
   * 
   * @param speed The speed to set the motor to.
   */
  public void setSpeed(Dimensionless speed) {
    setSpeed(speed.in(Value));
  }

  /** 
   * Stops the movement of the intake's motors.
   */
  public void stop() {
    mainMotor.stopMotor();
  }
}
