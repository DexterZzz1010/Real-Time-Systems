%%
K = 2.6133
Ti = 0.4523

beta = 0.5
h = 0.05

Kbeta = K * 0.5
KhTi = K * h / Ti

%%
n = 16 - 2 - 1

K_fix = fixpoint(K, n)
Kbeta_fix = fixpoint(Kbeta, n)
KhTi_fix = fixpoint(KhTi, n)