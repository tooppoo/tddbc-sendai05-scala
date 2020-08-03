package com.tooppoo

class GridPointSet private (g1: GridPoint, g2: GridPoint, grids: GridPoint*) {
  private val allGrids: Seq[GridPoint] = Seq(g1, g2) concat grids

  val count: Int = allGrids.size

  if (existDuplicateGrid) {
    throw new IllegalArgumentException("g1 and g2 must not same")
  }

  def notation: String = {
    val points = allGrids.map(_.notation).mkString(",")

    s"[$points]"
  }

  def contains(grid: GridPoint): Boolean = allGrids.contains(grid)

  def isConnected: Boolean = allGrids match {
    case g1 :: g2 :: Nil => g1 isNeighborOf g2
    case g1 :: g2 :: g3 :: Nil  if g1 isNeighborOf g2 =>
      (g1 isNeighborOf g3) || (g2 isNeighborOf g3)
    case g1 :: g2 :: g3 :: Nil  if g1 isNeighborOf g3 =>
      (g1 isNeighborOf g2) || (g2 isNeighborOf g3)
    case g1 :: g2 :: g3 :: Nil  if g2 isNeighborOf g3 =>
      (g1 isNeighborOf g2) || (g1 isNeighborOf g3)
    case _ => false
  }

  def isTraversable: Boolean = true

  private def existDuplicateGrid: Boolean = {
    val groupedByNotation = allGrids.groupBy(g => g.notation)

    // 重複が無ければ一致するはず
    allGrids.size != groupedByNotation.size
  }
}

object GridPointSet {
  def apply(g1: GridPoint, g2: GridPoint, grids: GridPoint*) =
    new GridPointSet(g1, g2, grids: _*)
}
