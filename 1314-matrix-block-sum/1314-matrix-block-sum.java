class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Step 1: Build 2D Prefix Sum Array with 1-based indexing
        int[][] pref = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pref[i + 1][j + 1] = mat[i][j] 
                                    + pref[i][j + 1] 
                                    + pref[i + 1][j] 
                                    - pref[i][j];
            }
        }

        int[][] ans = new int[m][n];

        // Step 2: Calculate sum for each cell (i, j) within [r1, c1] to [r2, c2]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Determine bounding box coordinates (clamped to matrix boundaries)
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                // Apply Inclusion-Exclusion formula using 1-based prefix sum array
                ans[i][j] = pref[r2 + 1][c2 + 1] 
                          - pref[r1][c2 + 1] 
                          - pref[r2 + 1][c1] 
                          + pref[r1][c1];
            }
        }

        return ans;
    }
}