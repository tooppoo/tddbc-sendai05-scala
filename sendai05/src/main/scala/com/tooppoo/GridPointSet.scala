package com.tooppoo

class GridPointSet private (g1: GridPoint, g2: GridPoint, grids: GridPoint*) {
  private val allGrids: Seq[GridPoint] = Seq(g1, g2) concat grids

  if (allGrids.groupBy(g => g.notation).size != allGrids.size) {
    throw new IllegalArgumentException("g1 and g2 must not same")
  }

  def contains(grid: GridPoint): Boolean = g1 == grid || g2 == grid

  def isConnected: Boolean = g1 isNeighborOf g2
}

object GridPointSet {
  def apply(g1: GridPoint, g2: GridPoint) =
    new GridPointSet(g1, g2)
  def apply(g1: GridPoint, g2: GridPoint, grids: GridPoint*) =
    new GridPointSet(g1, g2, grids: _*)
}
