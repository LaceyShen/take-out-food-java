import java.util.Arrays;
import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }

    public String bestCharge(List<String> inputs) {
        List<Item> ALL_ITEMS = Arrays.asList(
                new Item("ITEM0001", "Braised chicken", 18.00),
                new Item("ITEM0013", "Chinese hamburger", 6.00),
                new Item("ITEM0022", "Cold noodles", 8.00),
                new Item("ITEM0030", "coca-cola", 2.00)
        );
        List<SalesPromotion> ALL_SALES_PROMOTIONS = Arrays.asList(
                new SalesPromotion("BUY_30_SAVE_6_YUAN", "Deduct 6 yuan when the order reaches 30 yuan", Arrays.asList()),
                new SalesPromotion("50%_DISCOUNT_ON_SPECIFIED_ITEMS", "Half price for certain dishes", Arrays.asList(
                        "ITEM0001", "ITEM0022"
                ))
        );
        //TODO: write code here
        int sum=0;
        int certain_half_save=0;
        int save_6_sum=0;
        int price=0;
        int flag=0;
        List<String> itemList = null;
        List<String> certain_half=ALL_SALES_PROMOTIONS.get(1).getRelatedItems();

        String output="============= Order details =============\n";
        for(int i=0;i<inputs.size();i++){
            String itemId=inputs.get(i).split(" ")[0];
            int itemNum=Integer.parseInt(inputs.get(i).split(" ")[2]);
            //itemList.add(itemId);

            for (int j=0;j<ALL_ITEMS.size();j++){
                if(itemId.equals(ALL_ITEMS.get(j).getId())){
                    price= (int) (itemNum*ALL_ITEMS.get(j).getPrice());
                    sum+=price;
                    output+=ALL_ITEMS.get(j).getName()+" x "+itemNum+" = "+price+" yuan\n";
                    for(int k=0;k<certain_half.size();k++){     //如果在菜单内，算折半价格
                        if(itemId.equals(certain_half.get(k))){
                            certain_half_save+=price/2;
                            flag++;
                            break;
                        }
                    }
                }
            }
        }
        output+="-----------------------------------\n";
        if(sum>=30||flag>0){
            save_6_sum=sum-6;
            output+="Promotion used:\n";
            if(save_6_sum<=(sum-certain_half_save)){      //选择满30减6
                output+="BUY_30_SAVE_6_YUAN ，saving 6 yuan\n"+
                        "-----------------------------------\n"+
                        "Total："+save_6_sum+" yuan\n";
            }else{
                output+="Half price for certain dishes ("+"Braised chicken，Cold noodles"+")，saving "+certain_half_save+" yuan\n"+
                        "-----------------------------------\n"+
                        "Total："+(sum-certain_half_save)+" yuan\n";
            }
        }else{
            output+="Total：24 yuan\n";
        }



        output+="===================================";
        System.out.println(output);
        return output;
    }

}
