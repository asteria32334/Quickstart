package org.firstinspires.ftc.teamcode.general;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp
public class PinpointTestOpMode extends OpMode {

    private GoBildaPinpointDriver odo; // odometri değişken takımı

    @Override
    public void init() {

        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint"); // koddan cihazı çekme

        odo.setOffsets(-84.00, -168.00, DistanceUnit.MM); // robotun merkezine göre podların konumlar mm cinsinden
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD); //odometri cinsi
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD); //encoder yönleri

        odo.resetPosAndIMU(); //maç başlarken konum sıfırlama

        telemetry.addData("durum: ", "pinpoint hazır"); // bilgi
        telemetry.update(); //bilgi güncelleme
    }

    @Override
    public void loop() {
        odo.update(); //güncel kalması için

        Pose2D pos = odo.getPosition(); //pozisyon çekme

        double xEncoder = odo.getEncoderX(); // x podu
        double yEncoder = odo.getEncoderY(); // y podu
        double headingInDegrees = pos.getHeading(AngleUnit.DEGREES); //açı ölçü birimi

        telemetry.addData("status ", odo.getDeviceStatus()); //odometri durumunu alma
        telemetry.addData("frekans (hz)", odo.getFrequency()); //veri çekme hızı
        telemetry.addData("-------------------------------",""); //
        telemetry.addData("X konumu cm ", pos.getX(DistanceUnit.CM)); // cm olarak x podunun yol aldığı mesafe
        telemetry.addData("Y konumu cm", pos.getY(DistanceUnit.CM)); // cm olarak y podunun yol aldığı mesafe
        telemetry.addData("açı heading derece", headingInDegrees); // IMU nun derece olarak ölçtüğü açı
        telemetry.addData("Ham X tık encoder", xEncoder); // x podunun encoderının kaç tık aldığı
        telemetry.addData("Ham Y tık encoder", yEncoder); // y podunun encoderının kaç tık aldığı

        telemetry.update();

    }
}
