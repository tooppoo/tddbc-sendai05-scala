package com.tooppoo

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.prop.TableDrivenPropertyChecks

/**
 * [ ] 異なる座標を持つ２つの格子点を含む格子点集合(grid points)を導入します。
 *   [x] GridPointSet定義
 *   [x] GridPointSetは２つの格子点を持つ
 *     [x] 2つの格子点が異なる座標を持つ
 *     [x] 2つの格子点が同じ座標を持つ
 *   [x] 格子点集合が、指定した格子点を含む(contains)かを判定してください
 *     [x] 格子点Aと一致 : true
 *     [x] 格子点Bと一致 : true
 *     [x] 格子点A/B両方と不一致 : false
 *   [x] 格子点集合が連結している(connected)かを判定してください
 *       (補足) 格子点集合に含まれる２つの格子点が隣り合っている場合に限り、その格子点集合が連結しているものとします
 *     [x] AがBの左
 *     [x] AがBの左上
 *     [x] AがBの上
 *     [x] AがBの右上
 *     [x] AがBの右
 *     [x] AがBの右下
 *     [x] AがBの下
 *     [x] AがBの左下
 *  [x] 格子点集合を、異なる２つまたは３つの格子点を含むことが出来るように拡張してください
 *     [x] 3つの格子点を受け取れるようにする
 *       [x] 全ての格子点の座標が異なる: OK
 *       [x] 2つの格子点の座標が同じ  : NG
 *         [x] A・B・Cのうち、A・Bが一致
 *         [x] A・B・Cのうち、B・Cが一致
 *         [x] A・B・Cのうち、C・Aが一致
 *       [x] 全ての格子点の座標が同じ  : NG
 *     [x] 格子点を含むか
 *       [x] Aに一致
 *       [x] Bに一致
 *       [x] Cに一致
 *       [x] 全てに一致しない
 *     [x] 格子点集合に含まれる任意の格子点について
 *         その格子点から隣り合っている格子点のみを経由して
 *         その他全ての格子点へ到達できる場合に限り
 *         その格子点集合が連結しているものとします
 *         [x] A-B A-Cが連結 ( |_ ）: true
 *         [x] B-A B-Cが連結 ( |_ ）: true
 *         [x] C-A C-Bが連結 ( |_ ）: true
 *         [x] A-B B-Cが連結（ -- ) : true
 *         [x] A-C C-Bが連結（ -- ) : true
 *         [x] B-A A-Cが連結（ -- ) : true
 *         [x] A-B 連結 （ | . )   : false
 *         [x] B-C 連結 （ | . )   : false
 *         [x] A-C 連結 （ | . )   : false
 *         [x] 連結無し （ . . . )   : false
 *     [x] 格子点集合から、格子点集合に含まれている格子点の数(count)を取得してください
 *  [x] 格子点集合を、異なる２〜４つの格子点を含むことが出来るように拡張してください
 *     [x] 4つの格子点を受け取れるようにする
 *       [x] 全ての格子点の座標が異なる: OK
 *       [x] A・Dの座標が同じ  : NG
 *       [x] B・Dの座標が同じ  : NG
 *       [x] C・Dの座標が同じ  : NG
 *       [x] A・B・Dの座標が同じ  : NG
 *       [x] A・C・Dの座標が同じ  : NG
 *       [x] B・C・Dの座標が同じ  : NG
 *       [x] 全ての格子点の座標が同じ  : NG
 *  [ ] 格子点集合が一筆書きできるか(traversable)を判定してください
 *     (補足) 格子点集合に含まれるある格子点について
 *     その格子点から隣り合っている格子点のみを経由して
 *     最終的に全ての格子点をちょうど一度ずつ経由する組み合わせがある場合に限り
 *     その格子点集合が一筆書きできるものとします
 *     [ ] 2点
 *       [ ] 隣接している  : true
 *       [ ] 隣接していない: false
 *     [ ] 3点
 *     [ ] 4点
 */

class GridPointSetSpec extends AnyFunSpec with TableDrivenPropertyChecks {
  describe("格子点集合の生成") {
    describe("生成できる") {
      val cases = Table(
        ("case name", "create set"),
        (
          "2つの格子点が異なる座標を持つ",
          () => GridPointSet(GridPoint(4, 7), GridPoint(3, 6))
        ),
        (
          "3つの格子点が異なる座標を持つ",
          () => GridPointSet(GridPoint(4, 7), GridPoint(3, 6), GridPoint(2, 4))
        ),
        (
          "4つの格子点が異なる座標を持つ",
          () => GridPointSet(GridPoint(4, 7), GridPoint(3, 6), GridPoint(2, 4), GridPoint(1, 2))
        )
      )

      forAll(cases) { (caseName, create) =>
        it(caseName) {
          create()

          succeed // 例外が起きなければOK
        }
      }
    }

    describe("生成できない") {
      describe("格子点が2つ") {
        val cases = Table(
          ("case name", "create set"),
          ("2つの格子点が同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7)))
        )

        forAll(cases) { (caseName, create) =>
          it(caseName) {
            assertThrows[IllegalArgumentException] {
              create()
            }
          }
        }
      }
      describe("格子点が3つ[A, B, C]") {
        val cases = Table(
          ("case name", "create set"),
          ("A・Bが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(3, 2))),
          ("B・Cが同じ座標を持つ", () => GridPointSet(GridPoint(3, 2), GridPoint(4, 7), GridPoint(4, 7))),
          ("C・Aが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(3, 2), GridPoint(4, 7))),
          ("全て同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(4, 7))),
        )

        forAll(cases) { (caseName, create) =>
          it(caseName) {
            assertThrows[IllegalArgumentException] {
              create()
            }
          }
        }
      }
      describe("格子点が4つ[A, B, C, D]") {
        val cases = Table(
          ("case name", "create set"),
          ("A・Dが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(3, 7), GridPoint(2, 7), GridPoint(4, 7))),
          ("B・Dが同じ座標を持つ", () => GridPointSet(GridPoint(3, 7), GridPoint(4, 7), GridPoint(2, 7), GridPoint(4, 7))),
          ("C・Dが同じ座標を持つ", () => GridPointSet(GridPoint(3, 7), GridPoint(2, 7), GridPoint(4, 7), GridPoint(4, 7))),
          ("A・B・Dが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(2, 7), GridPoint(4, 7))),
          ("A・C・Dが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(2, 7), GridPoint(4, 7), GridPoint(4, 7))),
          ("B・C・Dが同じ座標を持つ", () => GridPointSet(GridPoint(2, 7), GridPoint(4, 7), GridPoint(4, 7), GridPoint(4, 7))),
          ("全て同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(4, 7), GridPoint(4, 7))),
        )

        forAll(cases) { (caseName, create) =>
          it(caseName) {
            assertThrows[IllegalArgumentException] {
              create()
            }
          }
        }
      }
    }
  }
  describe("文字列表記") {
    describe("[4,6], [4,7]") {
      val a = GridPoint(4, 6)
      val b = GridPoint(4, 7)
      val set = GridPointSet(a, b)
      it("[(4,6),(4,7)]") {
        assert(set.notation == "[(4,6),(4,7)]")
      }
    }
  }
  describe("格子点集合[A, B]が、指定した格子点を含む(contains)か") {
    describe("A: (4,7), B: (2, 5)") {
      val a = GridPoint(4, 7)
      val b = GridPoint(2, 5)
      val set = GridPointSet(a, b)

      val grids = Table(
        ("case name", "grid", "expected"),
        ("Aと一致", GridPoint(4, 7), true),
        ("Bと一致", GridPoint(2, 5), true),
        ("A/B両方と不一致", GridPoint(3, 6), false),
      )

      forAll(grids) { (caseName, grid, expected) =>
        describe(caseName) {
          describe(s"${grid.notation}") {
            assert((set contains grid) == expected)
          }
        }
      }
    }
    describe("A: (4,7), B: (2, 5), C: (3, 6)") {
      val a = GridPoint(4, 7)
      val b = GridPoint(2, 5)
      val c = GridPoint(3, 6)
      val set = GridPointSet(a, b, c)

      val grids = Table(
        ("case name", "grid", "expected"),
        ("Aと一致", GridPoint(4, 7), true),
        ("Bと一致", GridPoint(2, 5), true),
        ("Cと一致", GridPoint(3, 6), true),
        ("A/B/C全てと不一致", GridPoint(1, 2), false),
      )

      forAll(grids) { (caseName, grid, expected) =>
        describe(caseName) {
          it(s"${grid.notation}") {
            assert((set contains grid) == expected)
          }
        }
      }
    }
  }
  describe("格子点集合が連結している(connected)か") {
    describe("格子点がA,Bの2つ") {
      val set = Table(
        ("case name", "gird a", "grid b", "expected"),
        ("AがBの左隣", GridPoint(4, 7), GridPoint(5, 7), true),
        ("AがBの左上", GridPoint(4, 8), GridPoint(5, 7), false),
        ("AがBの上", GridPoint(5, 8), GridPoint(5, 7), true),
        ("AがBの右上", GridPoint(6, 8), GridPoint(5, 7), false),
        ("AがBの右", GridPoint(6, 7), GridPoint(5, 7), true),
        ("AがBの右下", GridPoint(6, 6), GridPoint(5, 7), false),
        ("AがBの下", GridPoint(5, 6), GridPoint(5, 7), true),
        ("AがBの左下", GridPoint(4, 6), GridPoint(5, 7), false),
      )

      forAll(set) { (caseName, gridA, gridB, expected) =>
        describe(caseName) {
          describe(s"A: ${gridA.notation} B: ${gridB.notation}") {
            it(s"$expected") {
              val set = GridPointSet(gridA, gridB)

              assert(set.isConnected == expected)
            }
          }
        }
      }
    }
    describe("格子点がA,B,Cの3つ") {
      val set = Table(
        ("case name", "gird a", "grid b", "grid c", "expected"),
        ("A-B, A-C 連結 |_", GridPoint(4, 4), GridPoint(4, 5), GridPoint(5, 4), true),
        ("B-A, B-C 連結 |_", GridPoint(4, 5), GridPoint(4, 4), GridPoint(5, 4), true),
        ("C-A, C-B 連結 |_", GridPoint(4, 5), GridPoint(5, 4), GridPoint(4, 4), true),
        ("A-B, B-Cが連結 --", GridPoint(4, 4), GridPoint(5, 4), GridPoint(6, 4), true),
        ("A-C, C-Bが連結 --", GridPoint(4, 4), GridPoint(6, 4), GridPoint(5, 4), true),
        ("B-A, A-Cが連結 --", GridPoint(5, 4), GridPoint(4, 4), GridPoint(6, 4), true),
        ("A-Bが連結 | .", GridPoint(4, 4), GridPoint(5, 4), GridPoint(6, 5), false),
        ("B-Cが連結 | .", GridPoint(6, 5), GridPoint(4, 4), GridPoint(5, 4), false),
        ("A-Cが連結 | .", GridPoint(4, 4), GridPoint(6, 5), GridPoint(5, 4), false),
        ("連結無し", GridPoint(4, 4), GridPoint(6, 5), GridPoint(5, 3), false),
      )

      forAll(set) { (caseName, gridA, gridB, gridC, expected) =>
        describe(caseName) {
          describe(
            s"A: ${gridA.notation} B: ${gridB.notation} C: ${gridC.notation}"
          ) {
            it(s"$expected") {
              val set = GridPointSet(gridA, gridB, gridC)

              assert(set.isConnected == expected)
            }
          }
        }
      }
    }
  }
  describe("格子点集合から、格子点集合に含まれている格子点の数(count)を取得") {
    describe("集合[A, B]") {
      it("2") {
        val set = GridPointSet(GridPoint(3, 4), GridPoint(4, 5))

        assert(set.count == 2)
      }
    }
  }
  describe("格子点集合が一筆書きできるか(traversable)") {
    describe("2点") {
      val set = Table(
        ("case name", "set", "expected"),
        ("隣接している", GridPointSet(GridPoint(4, 6), GridPoint(4, 7)), true),
        ("隣接していない", GridPointSet(GridPoint(4, 6), GridPoint(3, 7)), false),
      )

      forAll(set) { (caseName, set, expected) =>
        describe(caseName) {
          describe(set.notation) {
            it(s"$expected") {
              assert(set.isTraversable == expected)
            }
          }
        }
      }
    }
    describe("3点") {
      val set = Table(
        ("case name", "set", "expected"),
        ("隣接している: |_", GridPointSet(GridPoint(4, 4), GridPoint(4, 3), GridPoint(5, 3)), true),
        ("隣接している: --", GridPointSet(GridPoint(4, 4), GridPoint(5, 4), GridPoint(6, 4)), true),
        ("隣接していない: | .", GridPointSet(GridPoint(4, 4), GridPoint(4, 3), GridPoint(6, 3)), false),
        ("隣接していない: . . .", GridPointSet(GridPoint(4, 4), GridPoint(5, 5), GridPoint(6, 6)), false),
      )
      forAll(set) { (caseName, set, expected) =>
        describe(caseName) {
          describe(set.notation) {
            it(s"$expected") {
              assert(set.isTraversable == expected)
            }
          }
        }
      }
    }
    describe("4点") {
      val set = Table(
        (
          "case name",
          "set",
          "expected"
        ),
        (
          "先頭の点から一筆書きできる: |_|",
          GridPointSet(GridPoint(4, 4), GridPoint(4, 3), GridPoint(5, 3), GridPoint(5, 4)),
          true
        ),
        (
          "二番目の点から一筆書きできる: |_|",
          GridPointSet(GridPoint(4, 4), GridPoint(4, 5), GridPoint(5, 3), GridPoint(5, 4)),
          true
        ),
        (
          "一筆書きできない: | _",
          GridPointSet(GridPoint(4, 4), GridPoint(4, 3), GridPoint(6, 3), GridPoint(7, 3)),
          false
        ),
        (
          "一筆書きできない: _|_",
          GridPointSet(GridPoint(4, 4), GridPoint(3, 4), GridPoint(5, 4), GridPoint(4, 5)),
          false
        ),
      )
      forAll(set) { (caseName, set, expected) =>
        describe(caseName) {
          describe(set.notation) {
            it(s"$expected") {
              assert(set.isTraversable == expected)
            }
          }
        }
      }
    }
  }
}
