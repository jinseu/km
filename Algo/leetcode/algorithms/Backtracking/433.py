class Solution(object):

    def __init__(self, *args, **kwargs):
        super(Solution, self).__init__(*args, **kwargs)
        self.min_step = sys.maxint

    def dfs(self, step, step_cnt, start, end, bank):
        if start == end:
            self.min_step = min(self.min_step, step_cnt)
            return
        if step_cnt > self.min_step:
            return
        for mutat in bank:
            diff = 0
            for a, b in zip(start, mutat):
                if a != b:
                    diff += 1
                if diff > 1:
                    break
            if diff == 1 and mutat not in step:
                step[mutat] = 1
                self.dfs(step, step_cnt + 1, mutat, end, bank)
                del step[mutat]

    def minMutation(self, start, end, bank):
        """
        :type start: str
        :type end: str
        :type bank: List[str]
        :rtype: int
        """
        self.dfs({}, 0, start, end, bank)
        if self.min_step == sys.maxint:
            return -1
        return self.min_step
