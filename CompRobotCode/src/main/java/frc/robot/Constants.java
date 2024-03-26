// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = 80 * 0.453592; // 70lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(14)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag


  public static final class AutonConstants
  {

    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(0.4, 0, 0.01);
  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND  = 0.05;
    public static final double LEFT_Y_DEADBAND  = 0.05;
    public static final double RIGHT_X_DEADBAND = 0.05;
    public static final double RIGHT_Y_DEADBAND = 0.05;
    public static final double TURN_CONSTANT    = 6;
  }

  public static class ArmConstants
  {
    //// Old arm positions
    // public static final double launchPosition = 58.5; // TODO confirm this is best spot for launching
    // public static final double ampPosition = 53.8; // TODO check
    // public static final double intakePosition = 2.5; // TODO check 

    //Through bore encoder values
    public static final double launchPosition = 0.304; // TODO confirm this is best spot for launching
    public static final double ampPosition = 0.325; // TODO check
    public static final double intakePosition = 0.52; // TODO check 

    //Push-feed constants
    // public static final double stagePosition = null; //TODO position to stage Note before push
    // public static final double pushPosition = null; //TODO position to stage Note to push
  }

  //Old Swerve constants
  public static final class ModuleConstants {
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
    public static final double kDriveMotorGearRatio = 1 / 6.75;
    public static final double kTurningMotorGearRatio = 1 / 12.8;
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
    public static final double kPTurning = 0.5; //FIXME may need to adjust
  }

  public static final class DriveConstants {

    public static final double kTrackWidth = Units.inchesToMeters(21.5);
    // Distance between right and left wheels
    public static final double kWheelBase = Units.inchesToMeters(23.5);
    // Distance between front and back wheels
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
          new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
          new Translation2d(kWheelBase / 2, kTrackWidth / 2),
          new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
          new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

          public static final int kFrontLeftDriveMotorPort = 2;
          public static final int kBackLeftDriveMotorPort = 4;
          public static final int kFrontRightDriveMotorPort = 6;
          public static final int kBackRightDriveMotorPort = 8;
    
          public static final int kFrontLeftTurningMotorPort = 1;
          public static final int kBackLeftTurningMotorPort = 3;
          public static final int kFrontRightTurningMotorPort = 5;
          public static final int kBackRightTurningMotorPort = 7;

      

      public static final boolean kFrontLeftTurningEncoderReversed = true;
      public static final boolean kBackLeftTurningEncoderReversed = true;
      public static final boolean kFrontRightTurningEncoderReversed = true;
      public static final boolean kBackRightTurningEncoderReversed = true;

      public static final boolean kFrontLeftDriveEncoderReversed = false;
      public static final boolean kBackLeftDriveEncoderReversed = false;
      public static final boolean kFrontRightDriveEncoderReversed = false;
      public static final boolean kBackRightDriveEncoderReversed = false;

      public static final int kFrontLeftDriveAbsoluteEncoderPort = 0;
      public static final int kBackLeftDriveAbsoluteEncoderPort = 1;
      public static final int kFrontRightDriveAbsoluteEncoderPort = 3;
      public static final int kBackRightDriveAbsoluteEncoderPort = 2;

      public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
      public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
      public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
      public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;

      public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = -(Math.toRadians(290.2)); //TODO Confirm this shouldn't be positive, took from YAGSL config
      public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = -(Math.toRadians(147.9));
      public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = -(Math.toRadians(169.6));
      public static final double kBackRightDriveAbsoluteEncoderOffsetRad = -(Math.toRadians(62.3));

      public static final double kPhysicalMaxSpeedMetersPerSecond = 4.42;
      public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

      public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond * 0.75; //was divided by 2
      public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = 
              kPhysicalMaxAngularSpeedRadiansPerSecond/2; //was divided by 4
      public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3; //was 3 
      public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3; //was 3
    }

    public static final class AutoConstants {
        public static final double kAutonDriveSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        public static final double kPXController = 1.5;
        public static final double kPYController = 1.5;
        public static final double kPThetaController = 3;

        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
                new Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.03;
    }}
