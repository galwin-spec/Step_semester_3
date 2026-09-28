public class F3BusRouteRanking {
    static class BusRoute {
        private final String routeCode;
        private final String routeName;
        private final int priority;
        BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode; this.routeName = routeName; this.priority = priority;
        }
        BusRoute(String routeCode, String routeName) { this(routeCode, routeName, 1); }
        int compareTo(BusRoute other) {
            int result = Integer.compare(other.priority, this.priority);
            if (result != 0) return result;
            result = this.routeName.compareToIgnoreCase(other.routeName);
            if (result != 0) return result;
            return this.routeCode.compareToIgnoreCase(other.routeCode);
        }
        static BusRoute[] rankRoutes(BusRoute[] routes) {
            BusRoute[] ranked = routes.clone();
            for (int i = 1; i < ranked.length; i++) {
                BusRoute current = ranked[i]; int j = i - 1;
                while (j >= 0 && ranked[j].compareTo(current) > 0) { ranked[j + 1] = ranked[j]; j--; }
                ranked[j + 1] = current;
            }
            return ranked;
        }
    }
    public static void main(String[] args) {
        BusRoute[] routes = {new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4), new BusRoute("RT299T", "Night Service")};
        for (BusRoute route : BusRoute.rankRoutes(routes)) System.out.println(route.routeCode);
    }
}
