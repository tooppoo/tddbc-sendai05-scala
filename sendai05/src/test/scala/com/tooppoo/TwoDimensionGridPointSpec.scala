package com.tooppoo

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.prop.TableDrivenPropertyChecks

/**
 * [x] x座標とy座標を与えて格子点を生成してください
 * [x] 生成した格子点からx座標とy座標を取得してください
 * [x] 生成した格子点から文字列表記 (notation) を取得してください
 * [x] ２つの格子点が同じ座標(coordinates)を持つか判定してください
 *   [x] x/y座標が一致→true
 *   [x] x座標だけ同じ→false
 *   [x] y座標だけ同じ→false
 *   [x] x/y座標両方違う→false
 * [x] ２つの格子点が 隣り合っている(neighbor)かを判定しよう
 *   (補足) 格子点(x,y)は、４つの格子点(x-1,y),(x+1,y),(x,y-1),(x,y+1)と隣り合っているものとします
 *   [x] x-1 : true
 *   [x] x+1 : true
 *   [x] y-1 : true
 *   [x] y+1 : true
 *   [x] x-2 : false
 *   [x] x+2 : false
 *   [x] y-2 : false
 *   [x] y+2 : false
 *   [x] x-1 y-1 : false
 *   [x] x-1 y+1 : false
 *   [x] x+1 y-1 : false
 *   [x] x+1 y+1 : false
 *   [x] x+0 y+0 : false
 */

class TwoDimensionGridPointSpec extends AnyFunSpec with TableDrivenPropertyChecks {
  describe("文字列表記") {
    describe("x = 4, y = 7") {
      val p4_7 = GridPoint(4, 7)

      it("'(4,7)'") {
        assert(p4_7.notation == "(4,7)")
      }
    }
  }
  describe("座標取得") {
    describe("x = 4, y = 7") {
      val p4_7 = GridPoint(4, 7)

      describe("x座標") {
        it("4") {
          assert(p4_7.x == 4)
        }
      }
      describe("y座標") {
        it("7") {
          assert(p4_7.y == 7)
        }
      }
    }
  }
  describe("格子点Aと格子点Bが同じ座標を持つか") {
    describe("A = (4,7)") {
      val gridA = GridPoint(4, 7)

      val grids = Table(
        ("case name", "gridB", "expected"),
        ("xとyが一致", GridPoint(4, 7), true),
        ("xのみ一致", GridPoint(4, 6), false),
        ("yのみ一致", GridPoint(3, 7), false),
        ("xとy両方が不一致", GridPoint(3, 6), false),
      )

      forAll(grids) { (caseName, gridB, expected) =>
        describe(caseName) {
          describe(s"B = ${gridB.notation}") {
            it(s"$expected") {
              assert((gridA == gridB) == expected)
            }
          }
        }
      }
    }
  }
  describe("格子点Aと格子点Bが 隣り合っているか") {
    describe("A = (4,7)") {
      val gridA = GridPoint(4, 7)
      val grids = Table(
        ("case name", "gridB", "expected"),
        ("x-2", GridPoint(2, 7), false),
        ("x-1", GridPoint(3, 7), true),
        ("x+1", GridPoint(5, 7), true),
        ("x+2", GridPoint(6, 7), false),

        ("y-2", GridPoint(4, 5), false),
        ("y-1", GridPoint(4, 6), true),
        ("y+1", GridPoint(4, 8), true),
        ("y+2", GridPoint(4, 9), false),

        ("x-1 y-1", GridPoint(3, 6), false),
        ("x-1 y+1", GridPoint(3, 8), false),
        ("x+1 y-1", GridPoint(5, 6), false),
        ("x+1 y+1", GridPoint(5, 8), false),

        ("x+0 y+0", GridPoint(4, 7), false),
      )

      forAll(grids) { (caseName, gridB, expected) =>
        describe(caseName) {
          describe(s"B = ${gridB.notation}") {
            it(s"$expected") {
              assert((gridA isNeighborOf gridB) == expected)
            }
          }
        }
      }
    }
  }
}
