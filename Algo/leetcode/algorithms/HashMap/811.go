import (
    "strings"
    "strconv"
    "fmt"
)

func subdomainVisits(cpdomains []string) []string {
	countDomain := make(map[string]int)
	for _, v := range cpdomains {
		attrs := strings.Split(v, " ")
		domainC, _ := strconv.Atoi(attrs[0])
		domains := attrs[1]
		for domains != "" {
			value, exists := countDomain[domains]
			if exists {
				countDomain[domains] = value + domainC
			} else {
				countDomain[domains] = domainC
			}
			index := strings.Index(domains, ".")
			if index >= 0 {
				domains = domains[index+1:]
			} else {
				domains = ""
			}

		}
	}
	res := make([]string, len(countDomain))
	i := 0
	for k, v := range countDomain {
		res[i] = fmt.Sprintf("%d %s", v, k)
		i++
	}
	return res
}
