#!/usr/bin/python

import threading


class WorkerThread(threading.Thread):
    def __init__(self, name, cond, count):
        threading.Thread.__init__(self)
        self.name = name
        self.cond = cond
        self.count = count

    def run(self):
        self.cond.acquire()
        while True:
            self.cond.notify()
            print self.name, self.count
            self.count += 2
            if self.count > 10:
                break
            self.cond.wait()
        self.cond.release()


def main():
    lock = threading.Lock()
    cond = threading.Condition(lock)
    t1 = WorkerThread("w1", cond, 1)
    t2 = WorkerThread("w2", cond, 2)
    t1.start()
    t2.start()


if __name__ == "__main__":
    main()
