package com.tooppoo

import scala.annotation.tailrec

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

  def isConnected: Boolean = allGrids.forall(
    g => allGrids.filterNot(g.==).exists(g.isNeighborOf)
  )

  def isTraversable: Boolean = allGrids.exists(g =>
    canTraverseFrom(g, allGrids.filterNot(g.==))
  )

  /**
   * @return 一筆書きに成功したらtrue
   */
  @tailrec
  private def canTraverseFrom(start: GridPoint, candidate: Seq[GridPoint]): Boolean =
    candidate match {
      case last :: Nil => start isNeighborOf last
      case list => list.find(start.isNeighborOf) match {
        case Some(next) => canTraverseFrom(next, list.filterNot(next.==))
        case None => false
      }
    }

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
