import (
	"container/heap"
	"fmt"
)

type barcode struct {
	code int
	cnt  int
}

type barcodeHeap []barcode

func (h barcodeHeap) Len() int {
	return len(h)
}

func (h barcodeHeap) Less(i, j int) bool {
	return h[i].cnt-h[j].cnt > 0
}

func (h barcodeHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *barcodeHeap) Push(x interface{}) {
	*h = append(*h, x.(barcode))
}

func (h *barcodeHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func rearrangeBarcodes(barcodes []int) []int {
	codeMap := make(map[int]int)
	for _, code := range barcodes {
		if cnt, exist := codeMap[code]; exist {
			codeMap[code] = cnt + 1
		} else {
			codeMap[code] = 1
		}
	}
	bch := make(barcodeHeap, 0)
	for k, v := range codeMap {
		bch = append(bch, barcode{
			code: k,
			cnt:  v,
		})
	}
	heap.Init(&bch)
	res := make([]int, len(barcodes))
	for i := 0; i < len(res); i++ {
		first := heap.Pop(&bch).(barcode)
		res[i] = first.code
		if i > 0 && res[i] == res[i-1] {
			second := heap.Pop(&bch).(barcode)
			res[i] = second.code
			second.cnt --
			heap.Push(&bch, second)
		} else {
			first.cnt --
		}
		heap.Push(&bch, first)
	}
	return res
}