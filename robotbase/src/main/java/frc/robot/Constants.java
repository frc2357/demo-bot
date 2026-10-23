package frc.robot;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.units.measure.Dimensionless;

public class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotorPort = 11;
        // public static final int kLeftMotor2Port = 1;
        public static final int kRightMotorPort = 12;
        // public static final int kRightMotor2Port = 3;

        public static final int[] kLeftEncoderPorts = { 0, 1 };
        public static final int[] kRightEncoderPorts = { 2, 3 };
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;

        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 6;
        public static final double kEncoderDistancePerPulse =
                // Assumes the encoders are directly mounted on the wheel shafts
                (kWheelDiameterInches * Math.PI) / kEncoderCPR;
    }

    public static final class IntakeConstants {
        public static final int kMainMotorPort = 21;

        public static final int[] kEncoderPorts = {0, 2};
    }

    public static final class ControllerConstants {
        public static final int kDriverControllerPort = 0;
        public static final Dimensionless kDriverSpeed = Value.of(1);
    }
}
