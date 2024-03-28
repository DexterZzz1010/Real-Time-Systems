clear

A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = 0;
h = 0.05;

sys = ss(A, B, C, D);

sysd = c2d(sys, h, 'zoh');

% 定义期望的闭环极点和静态增益
p = 0.8 + 0.1i; % 选择一个闭环极点
p = [p; conj(p)]; % 闭环极点必须是共轭对

% 计算反馈矩阵 K
[K, ~] = place(sysd.a, sysd.b, p); % 使用极点配置方法
K

Kr = inv(sysd.c*inv(eye(2) - sysd.a + sysd.b*K)*sysd.b)

%%
Gammae = [sysd.b; 0]
Phie = [sysd.a sysd.b; zeros(1,2) 1]
Ce = [C 0];
Le = acker(Phie',Ce',[0.6+0.2i 0.6-0.2i 0.55])'

%%
n = 16-2-1

K1_fix = fixpoint(K(1), n)
K2_fix = fixpoint(K(2), n)

Kr_fix = fixpoint(Kr, n)

Phie11_fix = fixpoint(Phie(1), n)
Phie12_fix = fixpoint(Phie(2), n)
Phie13_fix = fixpoint(Phie(3), n)
Phie21_fix = fixpoint(Phie(4), n)
Phie22_fix = fixpoint(Phie(5), n)
Phie23_fix = fixpoint(Phie(6), n)
Phie31_fix = fixpoint(Phie(7), n)
Phie32_fix = fixpoint(Phie(8), n)
Phie33_fix = fixpoint(Phie(9), n)

Le1_fix = fixpoint(Le(1), n)
Le2_fix = fixpoint(Le(2), n)
Le3_fix = fixpoint(Le(3), n)