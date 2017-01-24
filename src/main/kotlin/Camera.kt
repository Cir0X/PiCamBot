import com.hopding.jrpicam.RPiCamera
import com.hopding.jrpicam.enums.AWB
import com.hopding.jrpicam.enums.DRC
import com.hopding.jrpicam.enums.Exposure
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class Camera {

    private val camera = RPiCamera("/home/pi/Pictures")

    init {
        camera
                .setWidth(1920)
                .setHeight(1080)
                .setFullPreviewOff()
                .setRotation(180)
                .setExposure(Exposure.AUTO)
                .setAWB(AWB.AUTO)
                .setDRC(DRC.HIGH)
                .setAddRawBayer(false)
                .setShutter(0) // auto
                .setTimeout(2)
                .setQuality(100)
    }

    fun takePhoto(): ByteArrayInputStream {
        val bufferedImage = camera.takeBufferedStill()
        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "JPG", byteArrayOutputStream);
        return ByteArrayInputStream(byteArrayOutputStream.toByteArray())
    }

}