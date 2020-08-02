package com.tooppoo

import org.scalatest.funspec.AnyFunSpec

class GridPointSpec extends AnyFunSpec {
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
}
