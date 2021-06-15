from torchvision.datasets import MNIST
import torchvision.transforms as transforms
from torch.utils.data import DataLoader

data_train = MNIST(
    "./data", download=True,
    transform=transforms.Compose([transforms.ToTensor(), transforms.Normalize((0.1037,), (0.3081,))])
)
data_test = MNIST(
    "./data", train=False, download=True,
    transform=transforms.Compose([transforms.ToTensor(), transforms.Normalize((0.1037,), (0.3081,))])
)
data_train_loader = DataLoader(data_train, batch_size=256, shuffle=True)
data_test_loader = DataLoader(data_train, batch_size=256, shuffle=True)

if __name__ == "__main__":
    (data, label) = data_test[0]
    print(data.shape)
    import matplotlib.pyplot as plt
    plt.imshow(data.reshape(28, 28), cmap='gray')
    plt.title('label is :{}'.format(label))
    plt.show()
    plt.savefig('data.png')