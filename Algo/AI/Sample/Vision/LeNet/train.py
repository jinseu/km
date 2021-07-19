import torch
import torch.nn as nn
import pickle

from data import data_train_loader, data_test_loader
from model import LeNet

m = LeNet()
m.train()
loss_function = nn.CrossEntropyLoss()
optimizer = torch.optim.SGD(m.parameters(), lr=0.01, momentum=0.9)
train_loss = 0
correct = 0
total = 0
loss_list = []

print('Start Training.')

for epoch in range(10):
    for batch_idx, data in enumerate(data_train_loader, start=0):
        train_loss = 0.0
        images, labels = data                       # 读取一个batch的数据
        optimizer.zero_grad()                       # 梯度清零，初始化
        outputs = m(images)                         # 前向传播
        loss = loss_function(outputs, labels)       # 计算误差
        loss.backward()                             # 反向传播
        optimizer.step()                            # 权重更新
        train_loss = loss.item()                 # 误差累计

        print('epoch:{} batch_idx:{} loss:{}'.format(epoch+1, batch_idx+1, train_loss))
        loss_list.append(train_loss)

print('Finished Training.')

torch.save(m, "./lenet.model", pickle_module=pickle, pickle_protocol=2)

import matplotlib.pyplot as plt
plt.plot(loss_list)
plt.title('traning loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.savefig('loss.png')