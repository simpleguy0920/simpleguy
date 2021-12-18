package com.test.java.solution;

import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionWidth {
    public static void main(String[] args) {


        SolutionWidth solutionWidth = new SolutionWidth();
//        String[] result = solutionWidth.trulyMostPopular1(
//                new String[]{"Fcclu(70)", "Ommjh(63)", "Dnsay(60)", "Qbmk(45)", "Unsb(26)", "Gauuk(75)", "Wzyyim(34)", "Bnea(55)", "Kri(71)", "Qnaakk(76)", "Gnplfi(68)", "Hfp(97)", "Qoi(70)", "Ijveol(46)", "Iidh(64)", "Qiy(26)", "Mcnef(59)", "Hvueqc(91)", "Obcbxb(54)", "Dhe(79)", "Jfq(26)", "Uwjsu(41)", "Wfmspz(39)", "Ebov(96)", "Ofl(72)", "Uvkdpn(71)", "Avcp(41)", "Msyr(9)", "Pgfpma(95)", "Vbp(89)", "Koaak(53)", "Qyqifg(85)", "Dwayf(97)", "Oltadg(95)", "Mwwvj(70)", "Uxf(74)", "Qvjp(6)", "Grqrg(81)", "Naf(3)", "Xjjol(62)", "Ibink(32)", "Qxabri(41)", "Ucqh(51)", "Mtz(72)", "Aeax(82)", "Kxutz(5)", "Qweye(15)", "Ard(82)", "Chycnm(4)", "Hcvcgc(97)", "Knpuq(61)", "Yeekgc(11)", "Ntfr(70)", "Lucf(62)", "Uhsg(23)", "Csh(39)", "Txixz(87)", "Kgabb(80)", "Weusps(79)", "Nuq(61)", "Drzsnw(87)", "Xxmsn(98)", "Onnev(77)", "Owh(64)", "Fpaf(46)", "Hvia(6)", "Kufa(95)", "Chhmx(66)", "Avmzs(39)", "Okwuq(96)", "Hrschk(30)", "Ffwni(67)", "Wpagta(25)", "Npilye(14)", "Axwtno(57)", "Qxkjt(31)", "Dwifi(51)", "Kasgmw(95)", "Vgxj(11)", "Nsgbth(26)", "Nzaz(51)", "Owk(87)", "Yjc(94)", "Hljt(21)", "Jvqg(47)", "Alrksy(69)", "Tlv(95)", "Acohsf(86)", "Qejo(60)", "Gbclj(20)", "Nekuam(17)", "Meutux(64)", "Tuvzkd(85)", "Fvkhz(98)", "Rngl(12)", "Gbkq(77)", "Uzgx(65)", "Ghc(15)", "Qsc(48)", "Siv(47)"},
//                new String[]{"(Gnplfi,Qxabri)", "(Uzgx,Siv)", "(Bnea,Lucf)", "(Qnaakk,Msyr)", "(Grqrg,Gbclj)", "(Uhsg,Qejo)", "(Csh,Wpagta)", "(Xjjol,Lucf)", "(Qoi,Obcbxb)", "(Npilye,Vgxj)", "(Aeax,Ghc)", "(Txixz,Ffwni)", "(Qweye,Qsc)", "(Kri,Tuvzkd)", "(Ommjh,Vbp)", "(Pgfpma,Xxmsn)", "(Uhsg,Csh)", "(Qvjp,Kxutz)", "(Qxkjt,Tlv)", "(Wfmspz,Owk)", "(Dwayf,Chycnm)", "(Iidh,Qvjp)", "(Dnsay,Rngl)", "(Qweye,Tlv)", "(Wzyyim,Kxutz)", "(Hvueqc,Qejo)", "(Tlv,Ghc)", "(Hvia,Fvkhz)", "(Msyr,Owk)", "(Hrschk,Hljt)", "(Owh,Gbclj)", "(Dwifi,Uzgx)", "(Iidh,Fpaf)", "(Iidh,Meutux)", "(Txixz,Ghc)", "(Gbclj,Qsc)", "(Kgabb,Tuvzkd)", "(Uwjsu,Grqrg)", "(Vbp,Dwayf)", "(Xxmsn,Chhmx)", "(Uxf,Uzgx)"});
//        int result = solutionWidth.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
//        int result= solutionWidth.numIslands(new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}}        );
        int result = solutionWidth.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}});
        System.out.println(result);
//        System.out.println(result);
    }

    public String[] trulyMostPopular1(String[] names, String[] synonyms) {
        if (names == null || synonyms == null) {
            return null;
        }
        LinkedList<TreeSet<String>> setList = new LinkedList<>();
        for (String synonym : synonyms) {
            String[] array = synonym.substring(1, synonym.length() - 1).split(",");
            List<TreeSet<String>> matchList = new ArrayList<>();
            for (TreeSet<String> set : setList) {
                if (set.contains(array[0]) || set.contains(array[1])) {
                    matchList.add(set);
                }
            }
            if (matchList.isEmpty()) {
                TreeSet treeSet = new TreeSet<>();
                treeSet.add(array[0]);
                treeSet.add(array[1]);
                setList.add(treeSet);
            } else {
                matchList.get(0).add(array[0]);
                matchList.get(0).add(array[1]);
                if (matchList.size() > 1) {
                    for (int i = 1; i < matchList.size(); i++) {
                        matchList.get(0).addAll(matchList.get(i));
                        setList.remove(matchList.get(i));
                    }
                }
            }
        }
        Map<String, String> sysynonymsMap = new HashMap<>();
        for (TreeSet<String> set : setList) {
            String root = set.first();
            for (String s : set) {
                sysynonymsMap.put(s, root);
            }
        }
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        for (String name : names) {
            String[] array = name.substring(0, name.length() - 1).split("\\(");
            int count = Integer.parseInt(array[1]);
            String startNme = array[0];
            String rootName = sysynonymsMap.get(startNme);
            if (rootName == null) {
                rootName = startNme;
            }
            Integer old = resultMap.get(rootName);
            if (old == null) {
                old = count;
            } else {
                old = old + count;
            }
            resultMap.put(rootName, old);
        }
        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            resultList.add(entry.getKey() + "(" + entry.getValue() + ")");
        }
        return resultList.toArray(new String[]{});
    }

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> numMap = new HashMap<>();
        for (String name : names) {
            String[] array = name.substring(0, name.length() - 1).split("\\(");
            numMap.put(array[0], Integer.valueOf(array[1]));
        }
        Map<String, String> synonymsMap = new HashMap<>();
        for (String synonym : synonyms) {
            String[] array = synonym.substring(1, synonym.length() - 1).split(",");
            String name1 = array[0];
            String name2 = array[1];
            while (synonymsMap.containsKey(name1)) {
                name1 = synonymsMap.get(name1);
            }
            while (synonymsMap.containsKey(name2)) {
                name2 = synonymsMap.get(name2);
            }
            String rootName;
            String childName;
            if (!name1.equals(name2)) {
                int flag = name1.compareTo(name2);
                if (flag < 0) {
                    rootName = name1;
                    childName = name2;
                } else {
                    rootName = name2;
                    childName = name1;
                }
                //合并
                int total = numMap.getOrDefault(name1, 0) + numMap.getOrDefault(name2, 0);
                numMap.put(rootName, total);
                numMap.remove(childName);
                //名字映射root
                synonymsMap.put(childName, rootName);
            }
        }

        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : numMap.entrySet()) {
            resultList.add(entry.getKey() + "(" + entry.getValue() + ")");
        }
        return resultList.toArray(new String[]{});
    }

    public int[] pondSizes(int[][] land) {
        if (land == null) {
            return new int[0];
        }
        List<Integer> resultList = new ArrayList<>();
        Set<Node> set = new HashSet<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                Node node = new Node(i, j);
                if (land[i][j] > 0) {
                    continue;
                } else if (set.contains(node)) {
                    continue;
                }
                int waterSize = 0;
                LinkedList<Node> queue = new LinkedList();
                queue.offer(node);
                set.add(node);
                waterSize++;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        Node parent = queue.poll();
                        List<Node> childList = new ArrayList<>();
                        Node node1 = new Node(parent.x, parent.y + 1);
                        Node node2 = new Node(parent.x, parent.y - 1);
                        Node node3 = new Node(parent.x + 1, parent.y);
                        Node node4 = new Node(parent.x - 1, parent.y);
                        Node node5 = new Node(parent.x - 1, parent.y - 1);
                        Node node6 = new Node(parent.x - 1, parent.y + 1);
                        Node node7 = new Node(parent.x + 1, parent.y - 1);
                        Node node8 = new Node(parent.x + 1, parent.y + 1);
                        childList.add(node1);
                        childList.add(node2);
                        childList.add(node3);
                        childList.add(node4);
                        childList.add(node5);
                        childList.add(node6);
                        childList.add(node7);
                        childList.add(node8);
                        for (Node child : childList) {
                            if (isWater(child, land, set)) {
                                queue.offer(child);
                                set.add(child);
                                waterSize++;
                            }
                        }
                    }
                }
                resultList.add(waterSize);
            }
        }
        int[] array = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            array[i] = resultList.get(i);
        }
        return array;
    }

    public boolean isWater(Node node, int[][] land, Set<Node> set) {
        return node.x >= 0 && node.x < land.length && node.y >= 0 && node.y < land[0].length && land[node.x][node.y] == 0 && !set.contains(node);
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(map.get(id), map);
    }

    public int dfs(Employee employee, Map<Integer, Employee> map) {
        int sum = employee.importance;
        if (employee.subordinates != null) {
            for (Integer subordinate : employee.subordinates) {
                sum = sum + dfs(map.get(subordinate), map);
            }
        }
        return sum;
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null) {
            return -1;
        }
        Map<Integer, Integer> resultMap = new HashMap<>();
        LinkedList<NodeTime> linkedList = new LinkedList<>();
        linkedList.offer(new NodeTime(K, 0));
        Map<Integer, List<NodeTime>> map = new HashMap<>();
        for (int[] time : times) {
            List<NodeTime> list = map.get(time[0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(new NodeTime(time[1], time[2]));
            map.put(time[0], list);
        }


        while (!linkedList.isEmpty()) {
            NodeTime root = linkedList.poll();
            List<NodeTime> list = map.get(root.node);
            if (list != null) {
                for (NodeTime node : list) {
                    int totalTime = root.time + node.time;
                    Integer oldTime = resultMap.get(node.node);
                    if (oldTime == null) {
                        resultMap.put(node.node, totalTime);
                        NodeTime nodeTime = new NodeTime(node.node, totalTime);
                        linkedList.offer(nodeTime);
                    } else if (totalTime < oldTime) {
                        resultMap.put(node.node, totalTime);
                        NodeTime nodeTime = new NodeTime(node.node, totalTime);
                        linkedList.offer(nodeTime);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i == K) {
                continue;
            }
            Integer time = resultMap.get(i);
            if (time == null) {
                return -1;
            }
            if (time > max) {
                max = time;
            }
        }
        return max;
    }

    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        boolean[][] visit = new boolean[board.length][board[0].length];

        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                if (visit[i][j] == true) {
                    continue;
                }
                Node root = new Node(i, j);
                LinkedList<Node> list = new LinkedList<>();
                List<Node> totalList = new ArrayList<>();
                list.offer(root);
                boolean changeFlag = true;
                while (!list.isEmpty()) {
                    int size = list.size();
                    for (int k = 0; k < size; k++) {
                        Node node = list.poll();
                        totalList.add(node);
                        Node up = new Node(node.x, node.y + 1);
                        Node down = new Node(node.x, node.y - 1);
                        Node left = new Node(node.x - 1, node.y);
                        Node right = new Node(node.x + 1, node.y);
                        List<Node> nodeList = new ArrayList<>();
                        nodeList.add(up);
                        nodeList.add(down);
                        nodeList.add(left);
                        nodeList.add(right);
                        for (Node node1 : nodeList) {
                            if (isValid(node1.x, node1.y, board) && board[node1.x][node1.y] == 'O' && !visit[node1.x][node1.y]) {
                                list.offer(node1);
                                visit[node1.x][node1.y] = true;
                                if (isEdge(node1.x, node1.y, board)) {
                                    changeFlag = false;
                                }
                            }
                        }
                    }
                }
                if (changeFlag) {
                    for (Node node : totalList) {
                        board[node.x][node.y] = 'X';
                    }
                }
            }
        }

    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                count++;
                Node root = new Node(i, j);
                LinkedList<Node> linkedList = new LinkedList<>();
                linkedList.offer(root);
                while (!linkedList.isEmpty()) {
                    int size = linkedList.size();
                    for (int k = 0; k < size; k++) {
                        Node node = linkedList.poll();
                        //左节点
                        if (node.x - 1 >= 0 && grid[node.x - 1][node.y] == '1' && !visited[node.x - 1][node.y]) {
                            Node left = new Node(node.x - 1, node.y);
                            linkedList.offer(left);
                            visited[node.x - 1][node.y] = true;
                        }
                        //右节点
                        if (node.x + 1 < grid.length && grid[node.x + 1][node.y] == '1' && !visited[node.x + 1][node.y]) {
                            Node right = new Node(node.x + 1, node.y);
                            linkedList.offer(right);
                            visited[node.x + 1][node.y] = true;
                        }
                        //下节点
                        if (node.y + 1 < grid[0].length && grid[node.x][node.y + 1] == '1' && !visited[node.x][node.y + 1]) {
                            Node down = new Node(node.x, node.y + 1);
                            linkedList.offer(down);
                            visited[node.x][node.y + 1] = true;
                        }
                        //上节点
                        if (node.y - 1 >= 0 && grid[node.x][node.y - 1] == '1' && !visited[node.x][node.y - 1]) {
                            Node up = new Node(node.x, node.y - 1);
                            linkedList.offer(up);
                            visited[node.x][node.y - 1] = true;
                        }
                    }
                }
            }
        }
        return count;
    }


    public int numSquares(int n) {
        int value = (int) Math.round(Math.sqrt(n));
        List<Integer> list = new ArrayList<>(value);
        for (int i = 1; i <= value; i++) {
            list.add(i * i);
        }
        if (n == value * value) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int num = Integer.MAX_VALUE;
            for (Integer integer : list) {
                if (i - integer < 0) {
                    break;
                }
                num = Math.min(num, dp[i - integer] + 1);
            }
            dp[i] = num;
        }
        return dp[n];
    }

    public int numSquaresWidth(int n) {
        if (n == 0) {
            return 0;
        }
        int value = (int) Math.round(Math.sqrt(n));
        List<Integer> list = new ArrayList<>(value);
        for (int i = 1; i <= value; i++) {
            list.add(i * i);
        }
        Collections.reverse(list);
        if (n == value * value) {
            return 1;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.offer(n);
        int count = 0;
        loop1:
        while (!linkedList.isEmpty()) {
            count++;
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                Integer node = linkedList.poll();
                for (Integer integer1 : list) {
                    int result = node - integer1;
                    if (result == 0) {
                        break loop1;
                    } else if (result > 0) {
                        linkedList.offer(result);
                    }
                }

            }
        }
        return count;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> list0 = edgeMap.getOrDefault(edge[0], new ArrayList<>());
            list0.add(edge[1]);
            edgeMap.put(edge[0], list0);
            List<Integer> list1 = edgeMap.getOrDefault(edge[1], new ArrayList<>());
            list1.add(edge[0]);
            edgeMap.put(edge[1], list1);
        }
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer root = i;
            int height = 0;
            Set<Integer> visit = new HashSet<>();
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.offer(root);
            visit.add(root);
            while (!linkedList.isEmpty()) {
                int size = linkedList.size();
                for (int j = 0; j < size; j++) {
                    Integer node = linkedList.poll();
                    List<Integer> list = edgeMap.get(node);
                    if (list != null) {
                        for (Integer integer : list) {
                            if (!visit.contains(integer)) {
                                linkedList.offer(integer);
                                visit.add(integer);
                            }
                        }
                    }
                }
                height++;
            }
            resultMap.put(root, height);
        }
        List<Integer> resultList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            if (entry.getValue() < min) {
                resultList.clear();
                resultList.add(entry.getKey());
                min = entry.getValue();
            } else if (entry.getValue() == min) {
                resultList.add(entry.getKey());
            }
        }
        return resultList;
    }

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer>[] edgeList = new List[n];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            edgeList[edge[0]].add(edge[1]);
            edgeList[edge[1]].add(edge[0]);
        }
        int min = Integer.MAX_VALUE;
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer root = i;
            int height = 0;
            int[] visit = new int[n];
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.offer(root);
            visit[root] = 1;
            while (!linkedList.isEmpty()) {
                int size = linkedList.size();
                for (int j = 0; j < size; j++) {
                    Integer node = linkedList.poll();
                    List<Integer> list = edgeList[node];
                    if (list != null) {
                        for (Integer integer : list) {
                            if (visit[integer] == 0) {
                                linkedList.offer(integer);
                                visit[integer] = 1;
                            }
                        }
                    }
                }
                height++;
            }
            if (height < min) {
                min = height;
                resultList = new ArrayList<>();
                resultList.add(root);
            } else if (height == min) {
                resultList.add(root);
            }
        }
        return resultList;
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < heightMap.length - 1; i++) {
            for (int j = 1; j < heightMap[0].length; j++) {
                int height = heightMap[i][j];
                int around = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    if (heightMap[k][j] < around) {
                        around = heightMap[k][j];
                    }
                }
                if (around <= height) {
                    continue;
                }
                for (int k = i + 1; k < heightMap.length; k++) {
                    if (heightMap[k][j] < around) {
                        around = heightMap[k][j];
                    }
                }
                if (around <= height) {
                    continue;
                }
                for (int k = 0; k < j; k++) {
                    if (heightMap[i][k] < around) {
                        around = heightMap[i][k];
                    }
                }
                if (around <= height) {
                    continue;
                }
                for (int k = j + 1; k < heightMap[0].length; k++) {
                    if (heightMap[i][k] < around) {
                        around = heightMap[i][k];
                    }
                }
                if (around <= height) {
                    continue;
                }
                sum = sum + around - height;
            }
        }
        return sum;
    }

    public boolean isValid(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public boolean isEdge(int x, int y, char[][] board) {
        return x == 0 || x == board.length - 1 || y == 0 || y == board[0].length - 1;
    }

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[1][];
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (isEdge(i, j, matrix)) {
                    matrix[i][j] = 1;
                    continue;
                }
                boolean[][] visit = new boolean[matrix.length][matrix[0].length];

                Node root = new Node(i, j);
                LinkedList<Node> linkedList = new LinkedList<>();
                linkedList.offer(root);
                visit[i][j] = true;
                int length = 1;
                loop1:
                while (!linkedList.isEmpty()) {
                    int size = linkedList.size();
                    for (int k = 0; k < size; k++) {
                        Node parent = linkedList.poll();
                        if (isEdge(parent.x, parent.y, matrix)) {
                            break loop1;
                        } else {
                            if (parent.x - 1 >= 0) {
                                linkedList.offer(new Node(parent.x - 1, parent.y));
                                visit[parent.x - 1][parent.y] = true;
                            }
                            if (parent.x + 1 < matrix.length) {
                                linkedList.offer(new Node(parent.x + 1, parent.y));
                                visit[parent.x + 1][parent.y] = true;
                            }

                            if (parent.y - 1 >= 0) {
                                linkedList.offer(new Node(parent.x, parent.y - 1));
                                visit[parent.x][parent.y - 1] = true;
                            }
                            if (parent.y + 1 < matrix[0].length) {
                                linkedList.offer(new Node(parent.x, parent.y + 1));
                                visit[parent.x][parent.y + 1] = true;
                            }
                        }
                    }
                    length++;
                }
                matrix[i][j] = length;
            }
        }
        return matrix;
    }

    public boolean isEdge(int x, int y, int[][] matrix) {
        if (x - 1 >= 0 && matrix[x - 1][y] == 0) {
            return true;
        } else if (x + 1 < matrix.length && matrix[x + 1][y] == 0) {
            return true;
        } else if (y - 1 >= 0 && matrix[x][y - 1] == 0) {
            return true;
        } else return y + 1 < matrix[0].length && matrix[x][y + 1] == 0;
    }

    public int orangesRotting(int[][] grid) {
        //烂橘子
        LinkedList<Node> list = new LinkedList<>();
        //新鲜橘子
        Set<Node> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    list.offer(new Node(i, j));
                } else if (grid[i][j] == 1) {
                    set.add(new Node(i, j));
                }
            }
        }
        //没有新鲜橘子
        if (set.isEmpty()) {
            return 0;
        }
        //访问过的
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int time = 0;
        while (!list.isEmpty()) {
            time++;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Node node = list.pop();
                //上
                if (node.y - 1 >= 0 && !visit[node.x][node.y - 1] && grid[node.x][node.y - 1] == 1) {
                    Node up = new Node(node.x, node.y - 1);
                    list.offer(up);
                    set.remove(up);
                    visit[node.x][node.y - 1] = true;
                }
                //下
                if (node.y + 1 <= grid[0].length - 1 && !visit[node.x][node.y + 1] && grid[node.x][node.y + 1] == 1) {
                    Node down = new Node(node.x, node.y + 1);
                    list.offer(down);
                    set.remove(down);
                    visit[node.x][node.y + 1] = true;
                }
                //左
                if (node.x - 1 >= 0 && !visit[node.x - 1][node.y] && grid[node.x - 1][node.y] == 1) {
                    Node left = new Node(node.x - 1, node.y);
                    list.offer(left);
                    set.remove(left);
                    visit[node.x - 1][node.y] = true;
                }
                //右
                if (node.x + 1 <= grid.length - 1 && !visit[node.x + 1][node.y] && grid[node.x + 1][node.y] == 1) {
                    Node right = new Node(node.x + 1, node.y);
                    list.offer(right);
                    set.remove(right);
                    visit[node.x + 1][node.y] = true;
                }
                if (set.isEmpty()) {
                    return time;
                }
            }
        }
        return -1;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //计算起始点映射map
        Map<Integer, List<NodeMoney>> map = new HashMap<>();
        for (int[] flight : flights) {
            List<NodeMoney> list = map.get(flight[0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(new NodeMoney(flight[1], flight[2]));
            map.put(flight[0], list);
        }
        LinkedList<NodeMoney> list = new LinkedList<>();
        list.offer(new NodeMoney(src, 0));
        int min = Integer.MAX_VALUE;
        int time = 0;
        while (!list.isEmpty()) {
            if (time > K) {
                break;
            }
            time++;
            int size = list.size();
            boolean allBigger = true;

            for (int i = 0; i < size; i++) {
                NodeMoney nodeMoney = list.pop();
                List<NodeMoney> nextNodeList = map.get(nodeMoney.getLocation());
                if (nextNodeList != null) {
                    for (NodeMoney nextNode : nextNodeList) {
                        int curremtMoney = nodeMoney.getMoney() + nextNode.getMoney();
                        if (nextNode.getLocation() == dst) {
                            min = Math.min(min, curremtMoney);
                        } else {
                            list.offer(new NodeMoney(nextNode.getLocation(), curremtMoney));
                        }
                        if (curremtMoney < min || min != Integer.MAX_VALUE) {
                            allBigger = false;
                        }
                    }

                }
            }
            if (allBigger) {
                break;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        //计算起始点映射map
        Map<Integer, List<NodeMoney>> map = new HashMap<>();
        for (int[] flight : flights) {
            List<NodeMoney> list = map.get(flight[0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(new NodeMoney(flight[1], flight[2]));
            map.put(flight[0], list);
        }
        LinkedList<NodeMoney> list = new LinkedList<>();
        list.offer(new NodeMoney(src, 0));
        int min = Integer.MAX_VALUE;
        int time = 0;
        while (!list.isEmpty()) {
            if (time > K) {
                break;
            }
            time++;
            int size = list.size();
            boolean allBigger = true;

            for (int i = 0; i < size; i++) {
                NodeMoney nodeMoney = list.pop();
                List<NodeMoney> nextNodeList = map.get(nodeMoney.getLocation());
                if (nextNodeList != null) {
                    for (NodeMoney nextNode : nextNodeList) {
                        int curremtMoney = nodeMoney.getMoney() + nextNode.getMoney();
                        if (nextNode.getLocation() == dst) {
                            min = Math.min(min, curremtMoney);
                        } else {
                            list.offer(new NodeMoney(nextNode.getLocation(), curremtMoney));
                        }
                        if (curremtMoney < min || min != Integer.MAX_VALUE) {
                            allBigger = false;
                        }
                    }

                }
            }
            if (allBigger) {
                break;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public int maxDistance(int[][] grid) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int distance = maxDistance(grid, i, j);
                    if (distance > 0 && distance < min) {
                        min = distance;
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public int maxDistance(int[][] grid, int i, int j) {
        LinkedList<Node> list = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Node node = new Node(i, j);
        list.offer(node);
        visited[i][j] = true;
        int time = 0;
        while (!list.isEmpty()) {
            time++;
            int size = list.size();
            for (int k = 0; k < size; k++) {
                Node parent = list.pop();
                if (parent.x - 1 >= 0 && !visited[parent.x - 1][parent.y]) {
                    if (grid[parent.x - 1][parent.y] == 1) {
                        return time;
                    } else {
                        list.offer(new Node(parent.x - 1, parent.y));
                        visited[parent.x - 1][parent.y] = true;
                    }
                }
                if (parent.x + 1 < grid.length && !visited[parent.x + 1][parent.y]) {
                    if (grid[parent.x + 1][parent.y] == 1) {
                        return time;
                    } else {
                        list.offer(new Node(parent.x + 1, parent.y));
                        visited[parent.x + 1][parent.y] = true;
                    }
                }
                if (parent.y - 1 >= 0 && !visited[parent.x][parent.y - 1]) {
                    if (grid[parent.x][parent.y - 1] == 1) {
                        return time;
                    } else {
                        list.offer(new Node(parent.x, parent.y - 1));
                        visited[parent.x][parent.y - 1] = true;
                    }
                }
                if (parent.y + 1 < grid[0].length && !visited[parent.x][parent.y + 1]) {
                    if (grid[parent.x][parent.y + 1] == 1) {
                        return time;
                    } else {
                        list.offer(new Node(parent.x, parent.y + 1));
                        visited[parent.x][parent.y + 1] = true;
                    }
                }

            }
        }
        return -1;
    }

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int length = 1000000;
        Set<String> visited = new HashSet<>(40000);
        for (int[] ints : blocked) {
            visited.add(ints[0] + ":" + ints[1]);
        }
        return isEscapePossible(source, target, length, visited) && isEscapePossible(target, source, length, visited);
    }

    private boolean isEscapePossible(int[] source, int[] target, int length, Set<String> visited) {
        Node root = new Node(source[0], source[1]);
        LinkedList<Node> list = new LinkedList<>();
        list.offer(root);
        visited.add(source[0] + ":" + source[1]);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (visited.size() > 20000) {
                    return true;
                }
                Node node = list.pop();
                if (node.x == target[0] && node.y == target[1]) {
                    return true;
                }
                if (node.x >= 1 && !visited.contains((node.x - 1) + ":" + node.y)) {
                    list.offer(new Node(node.x - 1, node.y));
                    visited.add((node.x - 1) + ":" + node.y);
                }
                if (node.x + 1 < length && !visited.contains((node.x + 1) + ":" + node.y)) {
                    list.offer(new Node(node.x + 1, node.y));
                    visited.add((node.x + 1) + ":" + node.y);
                }
                if (node.y >= 1 && !visited.contains(node.x + ":" + (node.y - 1))) {
                    list.offer(new Node(node.x, node.y - 1));
                    visited.add(node.x + ":" + (node.y - 1));
                }
                if (node.y + 1 < length && !visited.contains(node.x + ":" + (node.y + 1))) {
                    list.offer(new Node(node.x, node.y + 1));
                    visited.add(node.x + ":" + (node.y + 1));
                }
            }
        }
        return false;
    }

    public int kSimilarity(String A, String B) {
        StringBuilder bubuilderA = new StringBuilder();

        StringBuilder bubuildeB = new StringBuilder();

        return 0;

    }

    public int openLock(String[] deadends, String target) {
        int count = 0;
        Deque<String> deque = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        deque.offer("0000");
        visited.add("0000");
        for (String deadend : deadends) {
            visited.add(deadend);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String root = deque.poll();
                if (root.equals(target)) {
                    return count;
                }
                List<String> list = getNextString(new StringBuilder(root));
                for (String str : list) {
                    if (!visited.contains(str)) {
                        deque.offer(str);
                        visited.add(str);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public List<String> getNextString(StringBuilder stringBuilder) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char ch = stringBuilder.charAt(i);
            if (ch == '0') {
                stringBuilder.setCharAt(i, '9');
                list.add(stringBuilder.toString());
                stringBuilder.setCharAt(i, '1');
                list.add(stringBuilder.toString());
            } else if (ch == '9') {
                stringBuilder.setCharAt(i, '8');
                list.add(stringBuilder.toString());
                stringBuilder.setCharAt(i, '0');
                list.add(stringBuilder.toString());
            } else {
                stringBuilder.setCharAt(i, (char) (ch + 1));
                list.add(stringBuilder.toString());
                stringBuilder.setCharAt(i, (char) (ch - 1));
                list.add(stringBuilder.toString());
            }
            stringBuilder.setCharAt(i, ch);
        }
        return list;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        visited[0][0] = true;
        int count = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int value = deque.poll();
                int x = value / 1000;
                int y = value % 1000;
                if (x == grid.length - 1 && y == grid.length - 1) {
                    return count;
                }
                if (x - 1 >= 0 && y - 1 >= 0 && grid[x - 1][y - 1] == 0 && !visited[x - 1][y - 1]) {
                    deque.offer((x - 1) * 1000 + y - 1);
                    visited[x - 1][y - 1] = true;
                }
                if (x - 1 >= 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]) {
                    deque.offer((x - 1) * 1000 + y);
                    visited[x - 1][y] = true;
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]) {
                    deque.offer(x * 1000 + y - 1);
                    visited[x][y - 1] = true;
                }
                if (x + 1 < grid.length && y + 1 < grid.length && grid[x + 1][y + 1] == 0 && !visited[x + 1][y + 1]) {
                    deque.offer((x + 1) * 1000 + y + 1);
                    visited[x + 1][y + 1] = true;

                }
                if (x + 1 < grid.length && grid[x + 1][y] == 0 && !visited[x + 1][y]) {
                    deque.offer((x + 1) * 1000 + y);
                    visited[x + 1][y] = true;

                }
                if (y + 1 < grid.length && grid[x][y + 1] == 0 && !visited[x][y + 1]) {
                    deque.offer(x * 1000 + y + 1);
                    visited[x][y + 1] = true;

                }
                if (x - 1 >= 0 && y + 1 < grid.length && grid[x - 1][y + 1] == 0 && !visited[x - 1][y + 1]) {
                    deque.offer((x - 1) * 1000 + y + 1);
                    visited[x - 1][y + 1] = true;

                }
                if (x + 1 < grid.length && y - 1 >= 0 && grid[x + 1][y - 1] == 0 && !visited[x + 1][y - 1]) {
                    deque.offer((x + 1) * 1000 + y - 1);
                    visited[x + 1][y - 1] = true;
                }
            }
            count++;
        }
        return -1;
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Deque<NodeJump> deque = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        for (int i : forbidden) {
            visited.add(i);
        }
        deque.offer(new NodeJump(0, true));
        visited.add(0);
        // 往前跳和往后跳是两个Set集合
        Set<Integer> visitedLeft = new HashSet<>();
        Set<Integer> visitedRight = new HashSet<>();
        int count = 0;
        int max = x + a + b;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                NodeJump nodeJump = deque.poll();
                if (nodeJump.getPosition() == x) {
                    return count;
                }
                int nextRight = nodeJump.getPosition() + a;
                if (nextRight < max && !visited.contains(nextRight) && !visitedRight.contains(nextRight)) {
                    visitedRight.add(nextRight);
                    deque.offer(new NodeJump(nextRight, true));
                }
                if (nodeJump.isRight) {
                    int nextLeft = nodeJump.getPosition() - b;
                    if (nextLeft > 0 && !visited.contains(nextLeft) && !visitedLeft.contains(nextLeft)) {
                        visitedLeft.add(nextLeft);
                        deque.offer(new NodeJump(nextLeft, false));
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                Deque<Integer> deque = new ArrayDeque<>();
                visited[i][j] = true;
                int node = i + j * 100;
                deque.push(node);
                int count = 1;
                while (!deque.isEmpty()) {
                    int size = deque.size();
                    for (int k = 0; k < size; k++) {
                        int old = deque.poll();
                        int x = old % 100;
                        int y = (old - x) / 100;
                        if (y + 1 < grid[0].length && grid[x][y + 1] == 1 && !visited[x][y + 1]) {
                            visited[x][y + 1] = true;
                            deque.push(x + (y + 1) * 100);
                            count++;
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] == 1 && !visited[x][y - 1]) {
                            visited[x][y - 1] = true;
                            deque.push(x + (y - 1) * 100);
                            count++;
                        }
                        if (x + 1 < grid.length && grid[x + 1][y] == 1 && !visited[x + 1][y]) {
                            visited[x + 1][y] = true;
                            deque.push(x + 1 + y * 100);
                            count++;
                        }
                        if (x - 1 >= 0 && grid[x - 1][y] == 1 && !visited[x - 1][y]) {
                            visited[x - 1][y] = true;
                            deque.push(x - 1 + y * 100);
                            count++;
                        }
                    }

                }
                max = Math.max(max, count);
            }
        }
        return max;

    }


    class NodeJump {
        private int position;
        private boolean isRight;

        public NodeJump(int position, boolean isRight) {
            this.position = position;
            this.isRight = isRight;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean isRight() {
            return isRight;
        }

        public void setRight(boolean right) {
            isRight = right;
        }
    }

    class NodeMoney {
        private int money;
        private int location;

        public NodeMoney(int location, int money) {
            this.money = money;
            this.location = location;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class NodeTime {
        int node;
        int time;

        public NodeTime(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}