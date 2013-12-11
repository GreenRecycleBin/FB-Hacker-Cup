#!/usr/bin/env ruby -w

def min_move(n, k, c)
  # Optimal number of coins in each non-empty jars. Because we want to
  # maximize the number of non-empty jars, we minimize x.
  x = (c.to_f / n).ceil

  # Number of misses, i.e. number of empty jars
  m = n - [k / x, n].min

  m + c
end

t = gets.chomp.to_i

1.upto(t) do |i|
  n, k, c = gets.chomp.split(' ').map(&:to_i)

  puts 'Case #%d: %s' % [i, min_move(n, k, c)]
end