package com.tooppoo

case class GridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: GridPoint): Boolean = true
}
