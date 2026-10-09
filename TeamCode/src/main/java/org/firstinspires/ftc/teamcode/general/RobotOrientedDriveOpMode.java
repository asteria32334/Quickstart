package org.firstinspires.ftc.teamcode.general;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.general.mechanisms.MecanumDrive;

@TeleOp
public class RobotOrientedDriveOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    //diğer dosyadan değişken çekme
    double forward, strafe, rotate;
    //cihaz verisi çekme komutu
    @Override
    public void init() {
        drive.init(hardwareMap);
    }
    // güç kısımlarının kontrolcüdeki veri alma ayarları
    @Override
    public void loop() {
        forward = -gamepad1.right_stick_y;
        strafe = gamepad1.right_stick_x;
        rotate = gamepad1.left_stick_x;
        //diğer dosyaya iletme kodu
        drive.drive(forward,strafe,rotate);
    }
}
