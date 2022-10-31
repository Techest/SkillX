package skillx.core.utils

import com.mojang.math.Vector3f
import net.minecraft.util.Mth
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

@JvmInline
value class Degree(val degree: Float)

@JvmInline
value class Radian(val radian: Float)

fun Float.degree() = Degree(this)

fun Float.radian() = Radian(this)

fun Degree.toRadian() = (this.degree * 3.1415927F) / 180

fun Radian.toDegree() = (this.radian * 180) / 3.1415927F

fun Degree.invert() = Degree(-this.degree)

fun Radian.invert() = Radian(-this.radian)

fun rotateByAxis(toRotate: Vector3f, axis: Vector3f, degree: Degree): Vector3f {
    val quaternion = axis.rotationDegrees(degree.degree)
    toRotate.transform(quaternion)
    return toRotate
}

fun rotateByAxis(toRotate: Vector3f, axis: Vector3f, radian: Radian): Vector3f {
    val quaternion = axis.rotation(radian.radian)
    toRotate.transform(quaternion)
    return toRotate
}

fun Vector3f.distanceSqr(other: Vector3f): Float {
    val xSqr = (this.x() - other.x()).pow(2)
    val ySqr = (this.y() - other.y()).pow(2)
    val zSqr = (this.z() - other.z()).pow(2)

    return xSqr + ySqr + zSqr
}

fun Vector3f.distance(other: Vector3f): Float {
    return sqrt(distanceSqr(other))
}

fun Vector3f.angle(other: Vector3f): Radian {
    val sum1 = this.x().pow(2) + this.y().pow(2) + this.z().pow(2)
    val sum2 = other.x().pow(2) + other.y().pow(2) + other.z().pow(2)

    return acos(dot(other) * Mth.fastInvSqrt(sum1) * Mth.fastInvSqrt(sum2)).radian()
}