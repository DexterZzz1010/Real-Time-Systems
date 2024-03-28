clear
% Define continuous-time process
A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = 0;
% Sample the process using ZOH
h = 0.05;
[Phi,Gamma] = c2d(A,B,h)
Hp = ss(Phi,Gamma,C,0,h)
% 定义期望的闭环极点和静态增益
p = 0.8 + 0.1i; % 选择一个闭环极点
p = [p; conj(p)]; % 闭环极点必须是共轭对
% Calculate corresponding desired discrete-time poles
pd = exp(p*h)
% Design state feedback
K = place(Phi,Gamma,pd)
% Define augmented system and design observer
% 增广矩阵
Gammae = [Gamma; 0]
Phie = [Phi Gamma; zeros(1,2) 1];
Ce = [C 0];
Le = acker(Phie',Ce',[0.6+0.2i 0.6-0.2i 0.55])'
Ke = [K 1]
% Form complete controller
Hc = ss(Phie-Gammae*Ke-Le*Ce,Le,Ke,0,h)
bode(Hc) % draw controller Bode plot
margin(Hp*Hc) % check stability margins