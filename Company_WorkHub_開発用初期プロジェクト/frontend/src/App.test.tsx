import { render, screen } from '@testing-library/react';
import { expect, test } from 'vitest';
import App from './App';

test('初期ページを表示できる', () => {
  render(<App />);
  expect(screen.getByRole('heading', { name: 'Company WorkHub' })).toBeInTheDocument();
});
