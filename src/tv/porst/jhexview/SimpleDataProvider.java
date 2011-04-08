package tv.porst.jhexview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Data provider that provides data to the hex view component from a
 * static array. Use this data provider if you already have all the data
 * in memory and do not have to reload memory from an external source.
 */
public final class SimpleDataProvider implements IDataProvider {

	private Set<IDataChangedListener> _listeners;

	private final byte[] m_data;

	public SimpleDataProvider(final byte[] data) {
		this.m_data = data;
	}

	@Override
	public void addListener(final IDataChangedListener listener) {
		if (_listeners == null) {
			_listeners = new HashSet<IDataChangedListener>();
		}
		_listeners.add(listener);
	}

	@Override
	public void removeListener(final IDataChangedListener listener) {
		if (_listeners != null) {
			_listeners.remove(listener);
		}
	}

	@Override
	public byte[] getData() {
		return getData(0L, getDataLength());
	}

	@Override
	public byte[] getData(final long offset, final long length) {
		return Arrays.copyOfRange(this.m_data, (int) offset, (int) (offset + length));
	}

	@Override
	public long getDataLength() {
		return this.m_data.length;
	}

	public long getOffset() {
		return 0L;
	}

	@Override
	public boolean hasData(final long offset, final long length) {
		return true;
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public boolean keepTrying() {
		return false;
	}

	@Override
	public void setData(final long offset, final byte[] data) {
		System.arraycopy(data, 0, this.m_data, (int) offset, data.length);
		if (_listeners != null) {
			for (final IDataChangedListener l : _listeners) {
				l.dataChanged();
			}
		}
	}
}