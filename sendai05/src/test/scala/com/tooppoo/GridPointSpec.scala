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
    describe("x = 3, y = 1") {
      val p3_1 = GridPoint(3, 1)

      it("'(3,1)'") {
        assert(p3_1.notation == "(3,1)")
      }
    }
  }
}
