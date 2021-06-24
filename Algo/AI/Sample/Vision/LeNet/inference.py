import torch
import torch.nn as nn
import torchvision.transforms as transforms
import matplotlib.pyplot as plt
from model import LeNet
from data import data_test_loader
from PIL import Image

m = torch.load("lenet.model")

#I = Image.open('2.jpg')
I = Image.open('5.jpeg')
L = I.convert('L')
L = Image.eval(L, lambda x : 255 -x)
plt.imshow(L, cmap='gray')
plt.savefig("infer.jpeg")

transform=transforms.Compose([
        transforms.Resize((28,28)),
        transforms.ToTensor(),
        transforms.Normalize((0.1037,), (0.3081,))
])

im = transform(L)  # [C, H, W]
im = torch.unsqueeze(im, dim=0)  # [N, C, H, W]
print(im.shape)

with torch.no_grad():
    outputs = m(im)
    _, predict = torch.max(outputs.data, 1)
    print(predict)