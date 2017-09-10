class BakeryBestPrice {
    public static void main(String[] args) throws Exception {

        int baguette_prices[] = {10, 13};
        int baguette_packs[] = {3, 5};
        int maison_prices[] = {6, 10, 12};
        int maison_packs[] = {2, 5, 8};
        int toast_prices[] = {6, 9, 16};
        int toast_packs[] = {3, 5, 9};
        int price = 0;
        
        price = getBestPrice(baguette_prices, baguette_packs, 10);
        System.out.println("For a customer that wants to buy 10 Baguette "
                    + ( ( price > 0 ) ? "needs to pay " 
                    + price : "No matching packs available"));
        price = getBestPrice(maison_prices, maison_packs, 14);
        System.out.println("For a customer that wants to buy 14 Maison needs to pay "
                    + ( ( price > 0 ) ? "needs to pay " 
                    + price : "No matching packs available"));
        price = getBestPrice(toast_prices, toast_packs, 13);
        System.out.println("For a customer that wants to buy 13 Toast needs to pay "
                    + ( ( price > 0 ) ? "needs to pay " 
                    + price : "No matching packs available"));
    }
    public static int getBestPrice(int prices[], int packs[], int W) {

        int N = packs.length, value=0, flag=0; 
        int[][] V = new int[N + 1][W + 1]; 
        
        for (int col = 0; col <= W; col++) {
            V[0][col] = 0;
        }

        for (int row = 0; row <= N; row++) {
            V[row][0] = 0;
        }

        for (int item = 1; item <= N; item++) {
           
            for (int pack=1; pack<=W; pack++) {
                
                flag = 0;
                if (packs[item-1] <= pack){
                
                    if (pack % packs[item-1] == 0) {
                
                    value = (pack/packs[item-1]) * prices[item-1];
                    
                    } else {
                   
                   for(int j= packs[item-1]; j>=1; j--){
                       
                       if((pack >= (packs[item-1]*j)) && (V[item-1][pack-(packs[item-1] * j)]) > 0){
                           
                           flag = 1;
                           value = (prices[item-1]*j) + V[item-1][pack-(packs[item-1]*j)];
                           break;
                       }
                       
                   }

                   if(flag == 0)
                      value = 0;
                    }
                    
                    if((V[item-1][pack] == 0) && (flag == 0)&& ((value+V[item-1][pack-packs[item-1]]) > 0)) {
                        V[item][pack] = value+V[item-1][pack-packs[item-1]];
                    }
                    else if((V[item-1][pack] > 0) && (flag==0) && ((value+V[item-1][pack-packs[item-1]]) == 0)) {
                        V[item][pack] = V[item-1][pack];
                    }
                    else if (flag == 1) {
                        V[item][pack] = value; 
                    }
                    else {
                        V[item][pack] = Math.min (value + V[item-1][pack-packs[item-1]], V[item-1][pack]);
                    }
                }
                else {
                    V[item][pack] = V[item-1][pack];
                }
            }
        }

        return V[N][W];
    }
}
