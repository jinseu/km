import torch
import torch.nn as nn
from model import LeNet
from data import data_test_loader

m = torch.load("lenet.model")

print(type(m))

correct = 0  # 预测正确数
total = 0    # 总图片数

for data in data_test_loader:
    images, labels = data
    outputs = m(images)
    _, predict = torch.max(outputs.data, 1)
    total += labels.size(0)
    correct += (predict == labels).sum()

print('测试集准确率 {}%'.format(100*correct / total))