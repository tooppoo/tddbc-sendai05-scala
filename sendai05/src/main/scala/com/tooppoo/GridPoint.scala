package com.tooppoo

case class GridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: GridPoint): Boolean =
    isLeftFrom(other) ||
    isRightFrom(other) ||
    isUpFrom(other) ||
    isDownFrom(other)

  private def isLeftFrom(other: GridPoint): Boolean = x - 1 == other.x && y == other.y
  private def isRightFrom(other: GridPoint): Boolean = x + 1 == other.x && y == other.y
  private def isUpFrom(other: GridPoint): Boolean = x == other.x && y - 1 == other.y
  private def isDownFrom(other: GridPoint): Boolean = x == other.x && y + 1 == other.y
}
