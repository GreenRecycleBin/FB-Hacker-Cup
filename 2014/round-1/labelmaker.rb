#!/usr/bin/env ruby -w

def label(letters, n)
  result = ''

  while n > 0
    n -= 1
    char = letters[n % letters.size]
    result = char + result
    n /= letters.size
  end

  result
end

t = gets.chomp.to_i

1.upto(t) do |i|
  letters, n = gets.chomp.split(' ')
  n = n.to_i

  puts 'Case #%d: %s' % [i, label(letters, n)]
end