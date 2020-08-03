package com.tooppoo

case class GridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: GridPoint): Boolean =
    isLeftOf(other) ||
    isRightOf(other) ||
    isUpOf(other) ||
    isDownOf(other)

  private def isLeftOf(other: GridPoint): Boolean = x - 1 == other.x && y == other.y
  private def isRightOf(other: GridPoint): Boolean = x + 1 == other.x && y == other.y
  private def isUpOf(other: GridPoint): Boolean = x == other.x && y - 1 == other.y
  private def isDownOf(other: GridPoint): Boolean = x == other.x && y + 1 == other.y
}
