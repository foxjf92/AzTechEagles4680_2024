package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;


public class SwerveDrive extends SubsystemBase {
    public final Module frontLeft = new Module(
            DriveConstants.kFrontLeftDriveMotorPort,
            DriveConstants.kFrontLeftTurningMotorPort,
            DriveConstants.kFrontLeftDriveEncoderReversed,
            DriveConstants.kFrontLeftTurningEncoderReversed,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed);

    public final Module frontRight = new Module(
            DriveConstants.kFrontRightDriveMotorPort,
            DriveConstants.kFrontRightTurningMotorPort,
            DriveConstants.kFrontRightDriveEncoderReversed,
            DriveConstants.kFrontRightTurningEncoderReversed,
            DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
            DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontRightDriveAbsoluteEncoderReversed);

    public final Module backLeft = new Module(
            DriveConstants.kBackLeftDriveMotorPort,
            DriveConstants.kBackLeftTurningMotorPort,
            DriveConstants.kBackLeftDriveEncoderReversed,
            DriveConstants.kBackLeftTurningEncoderReversed,
            DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
            DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackLeftDriveAbsoluteEncoderReversed);

    public final Module backRight = new Module(
            DriveConstants.kBackRightDriveMotorPort,
            DriveConstants.kBackRightTurningMotorPort,
            DriveConstants.kBackRightDriveEncoderReversed,
            DriveConstants.kBackRightTurningEncoderReversed,
            DriveConstants.kBackRightDriveAbsoluteEncoderPort,
            DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackRightDriveAbsoluteEncoderReversed);

    //private static final double kPgain = 0.040;
    //private static final double kDgain = 0.0;

    private static SwerveDriveKinematics kinematics;

    // Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
    // Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
    // Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
    // Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);
    // SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
    //     m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);

    //private final AHRS gyro = new AHRS(I2C.Port.kOnboard);
    Pigeon2 m_imu;
    

    private SwerveDriveOdometry odometer = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, new Rotation2d(), new SwerveModulePosition[]{frontLeft.getpos(), frontRight.getpos(), backLeft.getpos(), backRight.getpos()});
    //private SwerveDriveOdometry odometer = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, new Rotation2d(0), null);
  
    public void resetEncoders() {
        frontLeft.resetEncoders();
        frontRight.resetEncoders();
        backLeft.resetEncoders();
        backRight.resetEncoders();
    }
    
    public SwerveDrive() {

        m_imu = new Pigeon2(17);

        kinematics = new SwerveDriveKinematics(
                new Translation2d(DriveConstants.kTrackWidth / 2.0, DriveConstants.kWheelBase / 2.0),
                new Translation2d(DriveConstants.kTrackWidth / 2.0, -DriveConstants.kWheelBase / 2.0),
                new Translation2d(-DriveConstants.kTrackWidth / 2.0, DriveConstants.kWheelBase / 2.0),
                new Translation2d(-DriveConstants.kTrackWidth / 2.0, -DriveConstants.kWheelBase / 2.0));

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e) {
            }
        }).start();
    }

    public void zeroHeading() {
        m_imu.setYaw(0);
    }

    public double getHeading() {
        return -Math.IEEEremainder(m_imu.getYaw(), 360);
    }

    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    public Pose2d getPose() {
        return odometer.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        odometer.resetPosition( getRotation2d(), new SwerveModulePosition[]{frontLeft.getpos(), frontRight.getpos(), backLeft.getpos(), backRight.getpos()}, pose);
    
    }


    @Override
    public void periodic() {
        odometer.update(getRotation2d(),new SwerveModulePosition[]{frontLeft.getpos(), frontRight.getpos(), backLeft.getpos(), backRight.getpos()});
        SmartDashboard.putNumber("Robot Heading", getHeading());
        // SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());
        // SmartDashboard.putNumber("Front Left Encoder; ", frontLeft.getAbsoluteEncoderRad() );
        // SmartDashboard.putNumber("Front Right Encoder; ", frontRight.getAbsoluteEncoderRad());
        // SmartDashboard.putNumber("Back Left Encoder; ", backLeft.getAbsoluteEncoderRad());
        // SmartDashboard.putNumber("Back Right Encoder; ", backRight.getAbsoluteEncoderRad());
        // SmartDashboard.putBoolean("Field Oriented", !RobotContainer.driveController.getRawButton(6));

    }
    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }

    
}