package com.tooppoo

case class GridPointSet(g1: GridPoint, g2: GridPoint, grids: GridPoint*) {
  def contains(grid: GridPoint): Boolean = g1 == grid || g2 == grid

  def isConnected: Boolean = g1 isNeighborOf g2

  if (g1 == g2) throw new IllegalArgumentException("g1 and g2 must not same")
}
