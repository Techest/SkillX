package skillx.utils

import com.mojang.math.Vector3f
import org.junit.jupiter.api.Test
import kotlin.math.abs

class MathTest {
    @Test
    fun testRotateByAxisDegree() {
        val toRotate = Vector3f(0F, 1F, 1F)
        val axis = Vector3f(1F, 0F, 0F)
        val degree = 180F.degree()
        val rotated = rotateByAxis(toRotate, axis, degree)
        assert(rotated.distanceSqr(Vector3f(0F, -1F, -1F)) < 0.0001)
    }

    @Test
    fun testRotateByAxisRadian() {
        val toRotate = Vector3f(0F, 1F, 1F)
        val axis = Vector3f(1F, 0F, 0F)
        val radian = 3.1415927F.radian()
        val rotated = rotateByAxis(toRotate, axis, radian)
        assert(rotated.distanceSqr(Vector3f(0F, -1F, -1F)) < 0.0001)
    }

    @Test
    fun testAngle() {
        val v1 = Vector3f(1F, 0F, 0F)
        val v2 = Vector3f(1F, 1F, 0F)
        val angle = v1.angle(v2)
        assert(abs(angle.radian - (3.1415927F / 4)) < 0.01) { "벡터 각도 측정 알고리즘 실패! 목표값: π/4, 측정값: ${angle.radian}" }
    }

    @Test
    fun nanTest() {
        val movement = Vector3f(-5.6064776E26F, 3.387764E26F, -6.25148E26F)
        val axis = Vector3f(-3.291542E27F, -3.2154165E27F, 1.20945495E27F)

        val angle = movement.angle(axis)

        assert(!angle.radian.isNaN()) { "각도가 NaN 입니다!" }
    }
}