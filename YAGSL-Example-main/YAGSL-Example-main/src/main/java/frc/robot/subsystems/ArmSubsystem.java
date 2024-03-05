package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ArmSubsystem extends SubsystemBase {
    
    private CANSparkMax armMotor; //Move to a certain position
    public double armspeed; 
    public RelativeEncoder armEncoder;

    // public final double kP = 0.0; // TODO - Tune this second
    // public final double kI = 0.0; // TODO - Tune this fourth
    // public final double kD = 0.0; // TODO - Tune this third
    // public final double arbFF = 0.0; //TODO - Tune this first
 
    public double setpoint = 1;
    public double errorSum = 0;
    public double lastTimestamp = 0;
    public double lastError = 0;
    public double test = 6.5;

    private float kUpperLimitArmMotor = 3;   //TODO confirm which was is up or down and set
    private float kLowerLimitArmMotor = 0;  //TODO confirm which way is up or down and set

    public ArmSubsystem(){
        armMotor = new CANSparkMax (9, CANSparkLowLevel.MotorType.kBrushless);
   
        armMotor.setSmartCurrentLimit(5);
        armMotor.setIdleMode(IdleMode.kBrake);
        armMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
        armMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        armMotor.setSoftLimit(SoftLimitDirection.kForward, kUpperLimitArmMotor); 
        armMotor.setSoftLimit(SoftLimitDirection.kReverse, kLowerLimitArmMotor);    
      
        armEncoder = armMotor.getEncoder();
    }

    @Override
    public void periodic(){
        SmartDashboard.putString("Test", "chickendog");
        SmartDashboard.putNumber("arm encoder: ", armEncoder.getPosition());
        SmartDashboard.putNumber("Test", 6.5);
    }

    public void moveArm(double armspeed) {
    armMotor.set(armspeed);
    }

}

