package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Value;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

/** The subsystem representing the robot's ability to move around. */
public class DriveSubsystem extends SubsystemBase {
    // The motor on the left side of the drive.
    private final TalonFX leftMotor = new TalonFX(DriveConstants.kLeftMotorPort);

    // The motor on the right side of the drive.
    private final TalonFX rightMotor = new TalonFX(DriveConstants.kRightMotorPort);

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        SendableRegistry.addChild(this, leftMotor);
        SendableRegistry.addChild(this, rightMotor);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        // leftEncoder.reset();
        // rightEncoder.reset();
    }

    /**
     * Gets the average distance of the TWO encoders.
     *
     * @return the average of the TWO encoder readings
     */
    public double getAverageEncoderDistance() {
        return 0; // (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output to which the drive will be constrained
     */
    public void setSpeed(double speed) {
        leftMotor.set(speed);
        rightMotor.set(speed);
    }

    /**
     * Sets the max output of the drive using a dimensionless value. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output to which the drive will be constrained
     */
    public void setSpeed(Dimensionless speed) {
        setSpeed(speed.in(Value));
    }

    /**
     * Sets the max output of the drive using linear velocity. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output in linear velocity to which the drive will be constrained
     */
    public void setSpeed(LinearVelocity speed) {
        setSpeed(speed.div(DriveConstants.kDistancePerSecond));
    }

    /**
     * Sets the max output of the drive using angular velocity. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param speed the maximum output in angular velocity to which the drive will be constrained
     */
    public void setSpeed(AngularVelocity speed) {
        setSpeed(speed.per(DriveConstants.kBaseAngularVelocityUnit));
    }

    /**
     * Adjusts the speed of the drive for differential driving.
     * 
     * @param speed The maximum speed of the motors.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(double speed, double forward, double rotation) {
        // SmartDashboard.putNumber("CallTime", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        leftMotor.set(speed * MathUtil.clamp(forward + rotation, -1, 1));
        rightMotor.set(speed * MathUtil.clamp(forward - rotation, -1, 1));
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(Dimensionless speed, Dimensionless forward, Dimensionless rotation) {
        differentialDrive(speed.in(Value), forward.in(Value), rotation.in(Value));
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors in linear velocity.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(LinearVelocity speed, Dimensionless forward, Dimensionless rotation) {
        differentialDrive(speed.div(DriveConstants.kDistancePerSecond), forward, rotation);
    }

    /**
     * Adjusts the speed of the drive for differential driving using the Units library.
     * 
     * @param speed The maximum speed of the motors in linear velocity.
     * @param forward The forward input to use.
     * @param rotation The rotation z value.
     */
    public void differentialDrive(AngularVelocity speed, Dimensionless forward, Dimensionless rotation) {
        differentialDrive(speed.per(DriveConstants.kBaseAngularVelocityUnit), forward, rotation);
    }

    /** 
     * Stops the movement of the drive.
     */
    public void stop() {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }
}
