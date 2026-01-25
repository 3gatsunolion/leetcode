class MovieRentingSystem {
    // private static class ShopMovie implements Comparable<ShopMovie> {
    private static class ShopMovie {
        int shop;
        int movie;
        int price;

        ShopMovie(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }

        // @Override
        // public int hashCode() {
        //     return Objects.hash(this.shop, this.movie);
        // }

        // @Override
        // public int compareTo(ShopMovie other) {
        //     if (this.price == other.price) {
        //         if (this.shop == other.shop) {
        //             return this.movie - other.movie;
        //         }
        //         return this.shop - other.shop;
        //     }
        //     return this.price - other.price;
        // }
    }

    private static final Comparator<ShopMovie> CMP = (a, b) -> {
            int c = Integer.compare(a.price, b.price);
            if (c != 0) return c;
            c = Integer.compare(a.shop, b.shop);
            if (c != 0) return c;
            return Integer.compare(a.movie, b.movie);
    };

    private Map<Integer, SortedSet<ShopMovie>> availableByMovie;
    private Map<Long, ShopMovie> inventory;
    private SortedSet<ShopMovie> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        availableByMovie = new HashMap<>();
        inventory = new HashMap<>();
        rented = new TreeSet<>(CMP);

        for (int[] entry : entries) {
            int shopId = entry[0], movieId = entry[1], price = entry[2];
            ShopMovie movie = new ShopMovie(shopId, movieId, price);
            availableByMovie.computeIfAbsent(movieId, _ -> new TreeSet<>(CMP))
                            .add(movie);
            // NOTE: each shop carries at most one copy of a movie
            // so just shop id and movie id will be unique
            inventory.put(encode(shopId, movieId), movie);
        }
    }
    
    public List<Integer> search(int movie) {
        SortedSet<ShopMovie> movies = availableByMovie.getOrDefault(movie, new TreeSet<>());
        List<Integer> res = movies.stream()
                                  .limit(5)
                                  .map(m -> m.shop)
                                  .collect(Collectors.toList());
        return res;
    }
    
    public void rent(int shop, int movie) {
        ShopMovie shopMovie = inventory.get(encode(shop, movie));
        availableByMovie.get(movie).remove(shopMovie);
        rented.add(shopMovie);
    }
    
    public void drop(int shop, int movie) {
        ShopMovie shopMovie = inventory.get(encode(shop, movie));
        rented.remove(shopMovie);
        availableByMovie.get(movie).add(shopMovie);
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>(5);
        Iterator<ShopMovie> it = rented.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            ShopMovie m = it.next();
            res.add(Arrays.asList(m.shop, m.movie));
        }
        return res;
        // List<List<Integer>> res = rented.stream()
        //                                 .limit(5)
        //                                 // .map(m -> List.of(m.shop, m.movie))
        //                                 .map(m -> Arrays.asList(m.shop, m.movie))
        //                                 .collect(Collectors.toList());
        // return res;
    }

    private long encode(int shop, int movie) {
        return (long) shop << 32 | movie;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */