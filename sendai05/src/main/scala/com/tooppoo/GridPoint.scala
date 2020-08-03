package com.tooppoo

trait WithXY {
  val x: Int
  val y: Int

  protected def isLeftOf(other: WithXY): Boolean = x - 1 == other.x && y == other.y
  protected def isRightOf(other: WithXY): Boolean = x + 1 == other.x && y == other.y
  protected def isUpOf(other: WithXY): Boolean = x == other.x && y - 1 == other.y
  protected def isDownOf(other: WithXY): Boolean = x == other.x && y + 1 == other.y
}

case class TwoDimensionGridPoint(x: Int, y: Int) extends WithXY {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isLeftOf(other) ||
    isRightOf(other) ||
    isUpOf(other) ||
    isDownOf(other)
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
