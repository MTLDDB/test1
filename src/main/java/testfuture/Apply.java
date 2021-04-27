//package testfuture;
//
//import java.util.Random;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//public class Apply {
//    public static void main(String[] args) {
//        try {
//            //thenApply();
//            handle();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void handle() throws Exception{
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//
//            @Override
//            public Integer get() {
//                int i= 10/1;
//                return new Random().nextInt(10);
//            }
//        }).handle(new BiFunction<Integer, Throwable, String>() {
//            @Override
//            public String apply(Integer param, Throwable throwable) {
//                String result = null;
//                if(throwable==null){
//                    return String.valueOf(param * 2);
//                }else{
//                    System.out.println(throwable.getMessage());
//                }
//                return result;
//            }
//        });
//        System.out.println(future.get());
//    }
//    private static void thenApply() throws Exception {
//        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
//            @Override
//            public Long get() {
//                long result = new Random().nextInt(100);
//                System.out.println("result1="+result);
//                return result;
//            }
//        }).thenApply(new Function<Long, Long>() {
//            @Override
//            public Long apply(Long t) {
//                long result = t*5;
//                System.out.println("result2="+result);
//                return result;
//            }
//        });
//
//        long result = future.get();
//        System.out.println(result);
//    }
//}
