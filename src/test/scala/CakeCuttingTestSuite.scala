// Copyright (c) 2020 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

import homework.CakeCutting._
import lecture.Calculus.almostEqual
import org.scalatest.FunSuite

class CakeCuttingTestSuite extends FunSuite {

  val reference = List((10,0.063281565095552948), (11,0.06218749444597419), (9,0.06212536907689728), (12,0.060378403698454944),
                       (8,0.060024511185408004), (13,0.05756074485919371), (7,0.056474674502400006), (14,0.05392999018345996),
                       (6,0.05149666368), (15,0.049692633811902386), (5,0.045172512), (16,0.04505465465612484),
                       (17,0.04021127928059142), (4,0.03764376), (18,0.03533861837953151), (19,0.030587537464061166),
                       (3,0.029105999999999996), (20,0.026079900364094258), (21,0.021907116305839174), (2,0.0198),
                       (22,0.018130746733118326), (23,0.014784799836006491), (24,0.011879265259539128), (1,0.01),
                       (25,0.009404418330468477), (26,0.007335446297765412), (27,0.005637008347282806), (28,0.00426742409698002),
                       (29,0.0031822791123193857), (30,0.0023373291411173417), (31,0.001690668078741544),
                       (32,0.0012041919735036547), (33,8.444396214194378E-4), (34,5.829192295737816E-4),
                       (35,3.9604218244571624E-4), (36,2.647824876922789E-4), (37,1.7416803634869902E-4),
                       (38,1.126914267618339E-4), (39,7.17073341868722E-5), (40,4.486305010665851E-5),
                       (41,2.7590775815594976E-5), (42,1.6675595724644963E-5), (43,9.902127556491556E-6),
                       (44,5.77547346783275E-6), (45,3.307771167940575E-6), (46,1.8597024566421456E-6),
                       (47,1.0260706162951664E-6), (48,5.553880101818943E-7), (49,2.948184687382222E-7),
                       (50,1.534259378127483E-7), (51,7.824722828450164E-8), (52,3.909292895468827E-8),
                       (53,1.912546370398595E-8), (54,9.15857110956912E-9), (55,4.290960168001828E-9),
                       (56,1.9660399315208376E-9), (57,8.805050264739749E-10), (58,3.852595677238761E-10),
                       (59,1.6459882910685607E-10), (60,6.862934230557049E-11), (61,2.7909265870931998E-11),
                       (62,1.1063049979657964E-11), (63,4.27176478246793E-12), (64,1.6056411118863587E-12),
                       (65,5.870625315334499E-13), (66,2.086329919757337E-13), (67,7.20099932910184E-14),
                       (68,2.4117973872394814E-14), (69,7.831247986801138E-15), (70,2.462870743675141E-15),
                       (71,7.494163834325789E-16), (72,2.2039174769115842E-16), (73,6.256676837232332E-17),
                       (74,1.7124438795603014E-17), (75,4.51252103397647E-18), (76,1.143171995274039E-18),
                       (77,2.779712956929505E-19), (78,6.476370187963026E-20), (79,1.443068126497402E-20),
                       (80,3.0688031044501713E-21), (81,6.214326286511596E-22), (82,1.1952988091833408E-22),
                       (83,2.177776123073063E-23), (84,3.746824462347391E-24), (85,6.066287224752924E-25),
                       (86,9.206482964625027E-26), (87,1.303894912896894E-26), (88,1.7145468739701683E-27),
                       (89,2.0808364334092496E-28), (90,2.314638279859728E-29), (91,2.3403564829692797E-30),
                       (92,2.1294672174709477E-31), (93,1.722090880215633E-32), (94,1.2184255905181562E-33),
                       (95,7.388325389312225E-35), (96,3.73304861775776E-36), (97,1.5087738163437606E-37),
                       (98,4.5729845567532664E-39), (99,9.239295328950493E-41), (100,9.332621544394462E-43))

  def check(data:List[(Int,Double)]) = {
    assert(100 == data.length)

    (1 to 100).foreach(m => {
      assert(data.exists { pair: (Int, Double) =>
        var (n: Int, _: Double) = pair
        n == m
      })
    })
    assert(almostEqual(0.001)(1.0, data.map(_._2).fold(0.0)(_ + _)))
    assert(data.take(10).map(_._1) == List(10, 11, 9, 12, 8, 13, 7, 14, 6, 15))
  }
  test("reference"){
    check(reference)
  }

  test("recursive cake cutting") {
    check(pieceSizesFold())

  }
  test("folding cake cutting"){
    check(pieceSizesRec())
  }
}
