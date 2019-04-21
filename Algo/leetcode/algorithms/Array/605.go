func canPlaceFlowers(flowerbed []int, n int) bool {
    couldFlower := 0 //0 is could
    flower := 0
    lengBed := len(flowerbed)
    for i := 0; i < lengBed; i++ {
        if flowerbed[i] == 0 && couldFlower == 0{
            flower += 1
            couldFlower = 1
        } else if flowerbed[i] == 1 {
            if couldFlower == 1 {
                flower -= 1
            }
            couldFlower = 1
        } else {
            couldFlower -= 1
        }
    }
    if n <= flower {
        return true
    }
    return false
    
}
